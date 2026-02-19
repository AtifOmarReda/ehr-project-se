package project.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.login.entities.BlacklistedToken;

import java.time.Instant;

@Repository
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {

    void deleteByExpiryDateBefore(Instant now);

    boolean existsByJti(String jti);

}