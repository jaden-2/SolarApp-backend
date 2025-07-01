package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.DTOs.CreatorRequest;
import com.jaden_2.solar.backend.exceptions.CredentialsUpdatedException;

import com.jaden_2.solar.backend.services.CreatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private CreatorService service;

    @PostMapping("/signup")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CreatorRequest payload){
        service.createUser(payload);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> usernameExists(@RequestParam("username") String username){
        return ResponseEntity.ok(service.existsByUsername(username));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody CreatorRequest payload, @AuthenticationPrincipal UserDetails principal){
        try{
            service.updateUser(payload, principal);
            return ResponseEntity.ok("Account updated");
        }catch (CredentialsUpdatedException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal UserDetails principal){
        service.deleteUser(principal);
        return ResponseEntity.accepted().build();
    }
}
