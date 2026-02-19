package project.login.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.login.entities.BlacklistedToken;
import org.springframework.transaction.annotation.Transactional;
import project.login.repositories.BlacklistedTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final BlacklistedTokenRepository repository;

    @Transactional
    public void blacklist(String jti, Instant expiryDate) {
        if (!repository.existsByJti(jti)) {
            repository.save(new BlacklistedToken(jti, expiryDate));
        }
    }

    public boolean isBlacklisted(String jti) {
        return repository.existsByJti(jti);
    }

}