package Project.management.config;

import Project.management.security.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import Project.management.security.JwtTokenFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import Project.management.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${app.gateway-secret}")
    private String gatewaySecretValue;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, CustomAccessDeniedHandler accessDeniedHandler, JwtTokenFilter jwtTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.accessDeniedHandler = accessDeniedHandler;
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
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore((request, response, chain) -> {
//                    jakarta.servlet.http.HttpServletRequest req = (jakarta.servlet.http.HttpServletRequest) request;
//                    jakarta.servlet.http.HttpServletResponse res = (jakarta.servlet.http.HttpServletResponse) response;
//                    String gatewaySecret = req.getHeader("X-Gateway-Secret");
//                    if (!gatewaySecretValue.equals(gatewaySecret)) {
//                        res.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
//                        res.setCharacterEncoding("UTF-8");
//                        res.getWriter().write("Accès interdit");
//                        return;
//                    }
//                    chain.doFilter(request, response);
//                }, UsernamePasswordAuthenticationFilter.class)
//                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
//                .portMapper(portMapper -> portMapper.http(8082).mapsTo(9082))
//                .csrf(csrf -> csrf.disable())
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/patients/**").authenticated()
//                        .requestMatchers("/medical-history-info/**").authenticated()
//                        .anyRequest().permitAll()
//                );
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    // Configuration pour https
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore((request, response, chain) -> {
                    jakarta.servlet.http.HttpServletRequest req = (jakarta.servlet.http.HttpServletRequest) request;
                    String gatewaySecret = req.getHeader("X-Gateway-Secret");
                    if (!gatewaySecretValue.equals(gatewaySecret)) {
                        jakarta.servlet.http.HttpServletResponse res = (jakarta.servlet.http.HttpServletResponse) response;
                        res.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
                        res.getWriter().write("Accès direct interdit");
                        return;
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class)
                // 2. SSL/TLS (HTTPS)
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .portMapper(portMapper -> portMapper.http(8082).mapsTo(9082))
                // 3. Désactivation CSRF ( Stateless API )
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler).accessDeniedHandler(accessDeniedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 4. Gestion fine des privilèges (Optimisé)
                .authorizeHttpRequests(auth -> auth
                        // Consultations : Sécurité granulaire
                        .requestMatchers(HttpMethod.POST, "/consultation/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/consultation/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.PUT, "/consultation/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.GET, "/consultation/**").hasAnyRole("ADMIN", "DOCTOR", "RECEPTIONIST")
                        // Autres ressources
                        .requestMatchers("/patients/**").authenticated()
                        .requestMatchers("/medical-history-info/**").authenticated()
                        // Tout le reste doit être authentifié
                        .anyRequest().authenticated()
                );
        // 5. Filtre JWT
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}