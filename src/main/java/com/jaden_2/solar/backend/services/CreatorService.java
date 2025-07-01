package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.CreatorResponse;
import com.jaden_2.solar.backend.DTOs.CreatorRequest;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.enums.Roles;
import com.jaden_2.solar.backend.exceptions.CredentialsUpdatedException;
import com.jaden_2.solar.backend.repositories.CreatorRepo;
import jakarta.validation.Valid;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatorService {
    private final CreatorRepo repo;
    private final PasswordEncoder encoder;
    public CreatorService(CreatorRepo repo, PasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;
    }

    public CreatorResponse getUser(UserDetails details){
        List<String> roles = new ArrayList<>();
        details.getAuthorities().forEach(authority->{
            roles.add(authority.getAuthority());
        });
        return new CreatorResponse(details.getUsername(), roles);
    }
    public Creator getUserByUsername(String username){
        return repo.findById(username).orElseThrow();
    }
    public void createUser(@Valid CreatorRequest request){
        
        request.setRole("USER");
        Creator user = new Creator(request.getUsername(), encoder.encode(request.getPassword()), Roles.valueOf(request.getRole().toUpperCase()));
        repo.save(user);
    }

    public void updateUser(@Valid CreatorRequest request, UserDetails userDetails){
        Creator user = repo.findById(userDetails.getUsername()).orElseThrow();
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(Roles.valueOf(request.getRole().toUpperCase()));

        boolean usernameChanged = !userDetails.getUsername().equals(request.getUsername());
        boolean roleChanged = !List.of(new SimpleGrantedAuthority(request.getRole().toUpperCase())).equals(userDetails.getAuthorities());

        if(usernameChanged || roleChanged){
            throw new CredentialsUpdatedException("Re-authenticate, credentials have been updated");
        }
        repo.save(user);
    }

    public void deleteUser(UserDetails userDetails){
        repo.deleteById(userDetails.getUsername());
    }
}
