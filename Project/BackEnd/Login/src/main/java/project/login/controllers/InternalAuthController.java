package project.login.controllers;

import lombok.RequiredArgsConstructor;
import project.login.security.JwtTokenProvider;
import project.login.services.TokenBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/internal/auth")
@RequiredArgsConstructor
public class InternalAuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @GetMapping("/is-blacklisted")
    public ResponseEntity<Boolean> checkBlacklist(@RequestParam("token") String token) {
        String jti = jwtTokenProvider.getJtiFromToken(token);
        boolean exists = tokenBlacklistService.isBlacklisted(jti);
        return ResponseEntity.ok(exists);
    }

}