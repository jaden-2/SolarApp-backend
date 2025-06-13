package com.jaden_2.solar.backend.configurations;

import com.jaden_2.solar.backend.jwt.JwtAuthenticationFilter;
import com.jaden_2.solar.backend.jwt.JwtUtil;
import com.jaden_2.solar.backend.jwt.JwtValidationFilter;
import com.jaden_2.solar.backend.services.AppUserDetailsService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

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
            request.anyRequest().permitAll();
        })
                .formLogin(FormLoginConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(configurer-> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authFilter)
                .addFilterBefore(validationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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
