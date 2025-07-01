package com.jaden_2.solar.backend.configurations;

import com.jaden_2.solar.backend.DTOs.AuthenticationDTO;
import com.jaden_2.solar.backend.jwt.JwtAuthenticationFilter;
import com.jaden_2.solar.backend.jwt.JwtUtil;
import com.jaden_2.solar.backend.jwt.JwtValidationFilter;
import com.jaden_2.solar.backend.services.AppUserDetailsService;
import com.jaden_2.solar.backend.services.CreatorTokenService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AppUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CreatorTokenService tokenService;
    @PersistenceContext
    EntityManager entityManager;
    @Value("${cors.allowed-origins}")
    List<String> allowedOrigins;

    @Bean
    SecurityFilterChain securityFilterChainConfig (HttpSecurity http, AuthenticationConfiguration config) throws Exception{
        AuthenticationManager manager = config.getAuthenticationManager();
        JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(jwtUtil, manager, entityManager, tokenService);
        JwtValidationFilter validationFilter = new JwtValidationFilter(jwtUtil, userDetailsService);
        return http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((request)->{
            request.requestMatchers(HttpMethod.GET, "/resources/*").permitAll();
            request.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
            request.requestMatchers(HttpMethod.GET, "/auth/refresh").permitAll();
            request.requestMatchers(HttpMethod.POST, "/account/signup").permitAll();
            request.requestMatchers(HttpMethod.GET, "/ping").permitAll();
            request.requestMatchers(HttpMethod.GET, "/account/exists").permitAll();
            request.requestMatchers(HttpMethod.GET, "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
            request.anyRequest().authenticated();
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
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", HttpMethod.PATCH.name(), "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider getAuthProvider(){
        DaoAuthenticationProvider manager = new DaoAuthenticationProvider(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder());
        return manager;
    }

    @Bean
    OpenAPI customConfiguration(){
        String securitySchemeName = "HttpOnlyCookies";

        PathItem loginPath = new PathItem()
                .post(new Operation()
                        .summary("Login endpoint")
                        .requestBody(new RequestBody()
                                .description("Username and password")
                                .required(true)
                                .content(new Content().addMediaType("application/json",
                                        new MediaType().schema(new Schema<AuthenticationDTO>()
                                                .addProperty("username", new StringSchema())
                                                .addProperty("password", new StringSchema())
                                        )
                                ))
                        )
                        .responses(new ApiResponses().addApiResponse("200",
                                new ApiResponse().description("Successful login")))
                );
        return new OpenAPI().info(new Info().title("Solar Estimation & Recommendation API Documentation")
                .version("Version 1.0")
                .summary("This is an API documentation for a solar estimation service for technical and non-technical personnel's. ")
                .contact(new Contact().email("jedidiahsylvanus@gmail.com")
                        .name("Sylvanus Jedidiah")
                        .url("https://github.com/jaden-2"))
                .description("I took it a step beyond estimation by " +
                        "integrating a database resource of various solar system components. Using these components and my estimation, a custom recommendation based on real " +
                                "components is created. This is bundled as a report that is modifiable, select a new resource that's in the database and the service generates a " +
                        "new report and configures selected resources to meet specification" +
                        "For authentication, I made use of cookie based authentication with rotation"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.COOKIE)
                        .name("AccessToken")))
                .path("/auth/login", loginPath);


    }
}
