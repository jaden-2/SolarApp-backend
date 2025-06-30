package com.jaden_2.solar.backend.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaden_2.solar.backend.services.AppUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AppUserDetailsService service;
    public JwtValidationFilter(JwtUtil util, AppUserDetailsService service){
        jwtUtil =util;
        this.service = service;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getCookies()== null){
            filterChain.doFilter(request, response);
            return;
        }
        try{
            Cookie[] cookies = request.getCookies();
            String accessToken = null;
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("AccessToken")){
                    accessToken = cookie.getValue();
                    break;
                }
            }
            if(StringUtils.hasText(accessToken)) {
                UserDetails user = service.loadUserByUsername(jwtUtil.extractUsername(accessToken));
                if (jwtUtil.isTokenValid(accessToken, user)) {

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired or Invalid token");
        }

    }
}
