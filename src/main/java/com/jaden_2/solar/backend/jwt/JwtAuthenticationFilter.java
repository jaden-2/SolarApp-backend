package com.jaden_2.solar.backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaden_2.solar.backend.DTOs.AuthenticationDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    private final AuthenticationManager authManager;
    public JwtAuthenticationFilter(JwtUtil service, AuthenticationManager authManager){
        this.jwtUtil = service;
        this.authManager = authManager;
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
        String jwtToken = jwtUtil.generateToken((UserDetails) authResult.getPrincipal());
        response.setContentType("application/json");
        response.setStatus(200);
        response.getWriter().write("{\n" + "\"token\":"+ "\""+jwtToken+ "\"" +"\n}" );
        response.getWriter().flush();
    }
}
