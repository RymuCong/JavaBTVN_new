package com.t3h.demo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.authorizeHttpRequests(configure -> configure
                .requestMatchers(HttpMethod.GET,"product").permitAll()
                .requestMatchers(HttpMethod.GET,"product/**").permitAll()
                .requestMatchers(HttpMethod.GET,"category").permitAll()
                .requestMatchers(HttpMethod.GET,"category/**").permitAll()
                .requestMatchers(HttpMethod.GET,"account").permitAll()
                .requestMatchers(HttpMethod.GET,"account/**").permitAll()
                .anyRequest().authenticated());

        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
