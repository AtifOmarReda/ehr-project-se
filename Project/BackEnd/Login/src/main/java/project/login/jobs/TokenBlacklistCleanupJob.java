package project.login.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.login.services.TokenBlacklistCleanupService;

@Component
@RequiredArgsConstructor
public class TokenBlacklistCleanupJob {

    private final TokenBlacklistCleanupService cleanupService;

    @Scheduled(cron = "0 */5 * * * *") // toutes les 5 minutes
    public void clean() {
        cleanupService.cleanExpiredTokens();
    }
}