package project.login.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import project.login.dto.LoginRequest;
import project.login.dto.JwtAuthenticationResponse;
import project.login.dto.RefreshRequest;
import project.login.dto.MessageResponse;
import project.login.entities.RefreshToken;
import project.login.entities.User;
import project.login.repositories.UserRepository;
import project.login.repositories.RefreshTokenRepository;
import project.login.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import project.login.services.AuthService;
import project.login.services.RefreshTokenService;
import project.login.services.TokenBlacklistService;
import project.login.exceptions.AuthenticationFailedException;
import project.login.exceptions.AlreadyAuthenticatedException;
import project.login.exceptions.InvalidRefreshTokenException;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final TokenBlacklistService tokenBlacklistService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String existingToken = jwtTokenProvider.resolveToken(request);
        if (existingToken != null && jwtTokenProvider.validateToken(existingToken)) {
            String jti = jwtTokenProvider.getJtiFromToken(existingToken);
            if (!tokenBlacklistService.isBlacklisted(jti)) {
                throw new AlreadyAuthenticatedException("Already authenticated.");
            }
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new AuthenticationFailedException("Username or password is incorrect"));
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
            return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken.getToken()));
        } catch (BadCredentialsException ex) {
            throw new AuthenticationFailedException("Username or password is incorrect");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new InvalidRefreshTokenException("Refresh token is invalid or expired"));
        if (refreshTokenService.isExpired(token)) {
            throw new InvalidRefreshTokenException("Refresh token expired");
        }
        User user = token.getUser();
        refreshTokenRepository.delete(token);
        RefreshToken newToken = refreshTokenService.createRefreshToken(user);
        String accessToken = jwtTokenProvider.generateAccessTokenFromUsername(user.getUsername());
        return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, newToken.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.ok(new MessageResponse("Successfully logged out (was already unauthenticated)"));
        }
        String username = authentication.getName();
        userRepository.findByUsername(username).ifPresent(authService::logout);
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String jti = jwtTokenProvider.getJtiFromToken(token);
            Instant expiry = jwtTokenProvider.getExpirationDate(token).toInstant();
            tokenBlacklistService.blacklist(jti, expiry);
        }
        return ResponseEntity.ok(new MessageResponse("Logged out"));
    }

}