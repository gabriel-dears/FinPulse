package com.fin_pulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register").permitAll()  // Allow unauthenticated access to login and register pages
                        .requestMatchers("/user/**").hasRole("USER")  // Only accessible by users with the "USER" role
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Only accessible by users with the "ADMIN" role
                        .anyRequest().authenticated()  // Require authentication for any other request
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()  // Custom login page
                )
                .logout(LogoutConfigurer::permitAll  // Allow logout for everyone
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }

}

