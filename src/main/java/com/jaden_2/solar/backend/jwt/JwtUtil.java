package com.jaden_2.solar.backend.jwt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String token;
    SecretKey secretKey;
    public static Duration ACCESSEXPIRATION = Duration.ofMinutes(15);// 15 minutes
    public static Duration REFRESHEXPIRATION = Duration.ofDays(1); // 24 hours
    public static ObjectMapper mapper = new ObjectMapper();
    public static String generateJti(){
        return UUID.randomUUID().toString();
    }
    @PostConstruct
    public void setSecretKey(){
        secretKey = Keys.hmacShaKeyFor(token.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(UserDetails user, String id, long jwtExpiration) {
        Map<String, List<String>> claims = new HashMap<>();
        claims.put("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claims(claims)
                .id(id)
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public List<SimpleGrantedAuthority> extractClaim(String token) throws JsonProcessingException {
        List<String> roles = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", List.class);

        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String extractTokenId(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getId();
    }
    public boolean isTokenExpired(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date(System.currentTimeMillis()));
    }

    public boolean isTokenValid(String token, UserDetails user){
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

}
