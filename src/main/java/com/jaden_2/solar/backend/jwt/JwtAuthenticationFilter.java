package com.jaden_2.solar.backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaden_2.solar.backend.DTOs.AuthenticationDTO;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.services.CreatorTokenService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper = new ObjectMapper();
    private final CreatorTokenService tokenService;
    private final AuthenticationManager authManager;
    private final EntityManager entityManager;
    //private final CreatorService creatorService;
    public JwtAuthenticationFilter(JwtUtil service, AuthenticationManager authManager, EntityManager entityManager, CreatorTokenService tokenService){
        this.jwtUtil = service;
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.entityManager = entityManager;
        setFilterProcessesUrl("/auth/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            var auth = mapper.readValue(request.getInputStream(), AuthenticationDTO.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(auth.username(), auth.password());
            return authManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String accessId = JwtUtil.generateJti();
        String accessToken = jwtUtil.generateToken((UserDetails) authResult.getPrincipal(), accessId, JwtUtil.ACCESSEXPIRATION.toMillis());
        String refreshId = JwtUtil.generateJti();
        String refreshToken= jwtUtil.generateToken((UserDetails) authResult.getPrincipal(), refreshId, JwtUtil.REFRESHEXPIRATION.toMillis());

        Creator creator = entityManager.getReference(Creator.class, ((UserDetails) authResult.getPrincipal()).getUsername());
        tokenService.createToken(refreshId, creator,JwtUtil.REFRESHEXPIRATION.toMillis());

        ResponseCookie accessCookie = ResponseCookie.from("AccessToken").value(accessToken)
                                        .path("/")
                                        .maxAge(JwtUtil.ACCESSEXPIRATION)
                                        .sameSite("None")
                                        .httpOnly(true)
                                        .secure(true)
                                        .build();

        ResponseCookie refreshCookie = ResponseCookie.from("RefreshToken").value(refreshToken)
                                        .path("/auth")
                                        .maxAge(JwtUtil.REFRESHEXPIRATION)
                                        .sameSite("None")
                                        .httpOnly(true)
                                        .secure(true)
                                        .build();

        response.addHeader("Set-Cookie", accessCookie.toString());
        response.addHeader("Set-Cookie", refreshCookie.toString());

        response.setStatus(200);
        response.getWriter().flush();
    }
}
