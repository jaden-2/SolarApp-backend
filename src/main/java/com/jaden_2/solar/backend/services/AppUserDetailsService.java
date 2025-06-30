package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.repositories.CreatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private CreatorRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Creator creator = repo.findById(username).orElseThrow();
        UserDetails user = User.builder().username(creator.getUsername())
                .password(creator.getPassword())
                .authorities(String.valueOf(new SimpleGrantedAuthority(creator.getRole().toString())))
                .build();
        return user;
    }
}
