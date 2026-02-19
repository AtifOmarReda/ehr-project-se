package project.login.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.login.repositories.BlacklistedTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenBlacklistCleanupService {

    private final BlacklistedTokenRepository repository;

    @Transactional
    public void cleanExpiredTokens() {
        repository.deleteByExpiryDateBefore(Instant.now());
    }

}