package Project.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Désactivé car on utilise du JWT (stateless)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/patients/**").authenticated() // Protège le CRUD
                        .anyRequest().permitAll()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                })); // Active la validation JWT
        return http.build();
    }

}