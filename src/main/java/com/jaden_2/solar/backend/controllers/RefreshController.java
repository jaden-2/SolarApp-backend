package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.jwt.JwtUtil;
import com.jaden_2.solar.backend.services.CreatorTokenService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class RefreshController {
    private final CreatorTokenService service;
    private final JwtUtil util;
    @PersistenceContext
    private EntityManager manager;
    RefreshController(CreatorTokenService tokenService, JwtUtil util){
        service = tokenService;
        this.util = util;
    }
    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request){
        String refreshToken= null;

        if(request.getCookies() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals("RefreshToken")) {
                refreshToken = cookie.getValue();
                break;
            }

        }
        if(!StringUtils.hasText(refreshToken))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if(service.isTokenValid(util.extractTokenId(refreshToken))){// validates, revokes, and set used
            String username = util.extractUsername(refreshToken);
            Collection<SimpleGrantedAuthority> authority;
            try{
                authority = util.extractClaim(refreshToken);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Creator creator = manager.getReference(Creator.class, username);
            UserDetails user = User.builder().username(username).password("").authorities(authority).build();

            String refreshId = JwtUtil.generateJti();
            String accessId = JwtUtil.generateJti();


            String accessToken= util.generateToken(user, accessId, JwtUtil.ACCESSEXPIRATION.toMillis());
            refreshToken = util.generateToken(user, refreshId, JwtUtil.REFRESHEXPIRATION.toMillis());

            service.createToken(refreshId, creator, JwtUtil.REFRESHEXPIRATION.toMillis()); // saves token to db

            ResponseCookie accessCookie = ResponseCookie.from("AccessToken").value(accessToken)
                    .maxAge(JwtUtil.ACCESSEXPIRATION)
                    .path("/")
                    .secure(true)
                    .httpOnly(true)
                    .sameSite("Strict")
                    .build();
            ResponseCookie refreshCookie = ResponseCookie.from("RefreshToken").value(refreshToken)
                    .maxAge(JwtUtil.REFRESHEXPIRATION)
                    .path("/auth")
                    .secure(true)
                    .httpOnly(true)
                    .sameSite("Strict")
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, accessCookie.toString());
            headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
           return ResponseEntity.ok().headers(headers).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/logout")
    ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails authUser){
        Creator creator = manager.getReference(Creator.class, authUser.getUsername());
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.SET_COOKIE, ResponseCookie.from("RefreshToken")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .path("/auth")
                .maxAge(Duration.ZERO)
                .build().toString());
        headers.add(HttpHeaders.SET_COOKIE, ResponseCookie.from("AccessToken")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ZERO)
                .build().toString());
        return ResponseEntity.ok().headers(headers).build();
    }
}
