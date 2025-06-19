package com.jaden_2.solar.backend.configurations;

import com.jaden_2.solar.backend.jwt.JwtAuthenticationFilter;
import com.jaden_2.solar.backend.jwt.JwtUtil;
import com.jaden_2.solar.backend.jwt.JwtValidationFilter;
import com.jaden_2.solar.backend.services.AppUserDetailsService;
import com.zaxxer.hikari.HikariDataSource;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AppUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Bean
    SecurityFilterChain securityFilterChainConfig (HttpSecurity http, AuthenticationConfiguration config) throws Exception{
        AuthenticationManager manager = config.getAuthenticationManager();
        JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(jwtUtil, manager);
        JwtValidationFilter validationFilter = new JwtValidationFilter(jwtUtil, userDetailsService);
        return http
                .authorizeHttpRequests((request)->{
            request.requestMatchers(HttpMethod.GET, "/resources/*").permitAll();
            request.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
            request.requestMatchers(HttpMethod.POST, "/account/signup").permitAll();
            request.anyRequest().authenticated();
        })
                .formLogin(FormLoginConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(configurer-> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authFilter)
                .addFilterBefore(validationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET"));
        config.setAllowedHeaders(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    AuthenticationProvider getAuthProvider(){
        DaoAuthenticationProvider manager = new DaoAuthenticationProvider(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder());
        return manager;
    }
}
