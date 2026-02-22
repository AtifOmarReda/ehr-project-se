package project.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.login.security.JwtAuthenticationEntryPoint;
import project.login.security.JwtTokenFilter;


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
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/login", "/auth/refresh").permitAll()
//                        .requestMatchers("/auth/logout").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()
//                );
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    // Configuration pour https
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .portMapper(portMapper -> portMapper.http(8081).mapsTo(9081))
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Gestion des autorisations
                .authorizeHttpRequests(auth -> auth
                        // Accès Public
                        .requestMatchers("/auth/login", "/auth/refresh").permitAll()
                        .requestMatchers("/auth/logout").authenticated()
                        // Accès Admin uniquement
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Accès Docteur uniquement
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        // Accès Réceptionniste uniquement
                        .requestMatchers("/reception/**").hasRole("RECEPTIONIST")
                        // Accès Partagé (ex: Patient info accessible par Docteur et Réceptionniste)
                        .requestMatchers("/medical-records/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/appointments/**").hasAnyRole("RECEPTIONIST", "DOCTOR", "ADMIN")
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}