//package com.jaden_2.solar.backend.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jaden_2.solar.backend.DTOs.AuthenticationDTO;
//import com.jaden_2.solar.backend.services.AppUserDetailsService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    @Autowired
//    private AppUserDetailsService service;
//    @Autowired
//    private JwtUtil jwtUtil;
//    private ObjectMapper mapper = new ObjectMapper();
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try{
//            var auth = mapper.readValue(request.getInputStream(), AuthenticationDTO.class);
//            UserDetails user = service.loadUserByUsername(auth.username());
//            if(user!=null && SecurityContextHolder.getContext()==null){
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return super.attemptAuthentication(request, response);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//}
