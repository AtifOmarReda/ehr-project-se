package Project.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import Project.management.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable()) // Désactivé car on utilise du JWT (stateless)
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/patients/**").authenticated() // Protège le CRUD
//                        .anyRequest().permitAll()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
//                })); // Active la validation JWT
//        return http.build();
//    }

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Très important : pas de session côté serveur (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/patients/**").authenticated() // On protège nos routes
                        .anyRequest().permitAll()
                );

        // On injecte notre filtre AVANT le filtre standard de Spring
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}