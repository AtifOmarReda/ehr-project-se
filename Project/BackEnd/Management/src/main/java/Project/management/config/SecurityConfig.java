package Project.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import Project.management.security.JwtTokenFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import Project.management.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenFilter jwtTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    // Configuration pour http
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/patients/**").authenticated()
//                        .anyRequest().permitAll()
//                );
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    // Configuration pour https
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .portMapper(portMapper -> portMapper.http(8082).mapsTo(9082))
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Gestion des autorisations
                .authorizeHttpRequests(auth -> auth
                        // Accès Public
                        .requestMatchers("/patients/**").authenticated()
                        .requestMatchers("/medical-history-info/**").authenticated()
                        .anyRequest().permitAll()
                );
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}