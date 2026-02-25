package project.login.controllers;

import lombok.RequiredArgsConstructor;
import project.login.repositories.UserRepository;
import project.login.security.JwtTokenProvider;
import project.login.services.TokenBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/internal/auth")
@RequiredArgsConstructor
public class InternalAuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserRepository userRepository;

    @GetMapping("/is-blacklisted")
    public ResponseEntity<Boolean> checkBlacklist(@RequestParam("token") String token) {
        String jti = jwtTokenProvider.getJtiFromToken(token);
        boolean exists = tokenBlacklistService.isBlacklisted(jti);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/is-user-doctor/{id}")
    public ResponseEntity<Boolean> isUserDoctor(@PathVariable("id") Long id) {
        return userRepository.findById(id).map(user -> ResponseEntity.ok(user.getIsDoctor())).orElse(ResponseEntity.notFound().build());
    }

}