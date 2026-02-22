package Project.management.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RemoteBlacklistService {

    private final RestTemplate restTemplate;

    @Value("${services.auth.url}")
    private String authServiceBaseUrl;

    @Value("${services.auth.check-blacklist-endpoint}")
    private String blacklistEndpoint;

//    public RemoteBlacklistService(RestTemplateBuilder restTemplateBuilder) {
//        // Il est préférable d'injecter RestTemplate via un Builder
//        this.restTemplate = restTemplateBuilder.build();
//    }

    // MODIFICATION ICI : On injecte directement le RestTemplate (le Bean configuré)
    public RemoteBlacklistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean isTokenBlacklisted(String token) {
        try {
            // Attention : UriComponentsBuilder gère déjà les slashes
            String fullUrl = UriComponentsBuilder.fromHttpUrl(authServiceBaseUrl)
                    .path(blacklistEndpoint)
                    .queryParam("token", token)
                    .toUriString();

            Boolean isBlacklisted = restTemplate.getForObject(fullUrl, Boolean.class);
            return Boolean.TRUE.equals(isBlacklisted);
        } catch (Exception e) {
            // On affiche l'erreur complète pour débugger au début
            e.printStackTrace();
            return true; // Fail-safe : on bloque si le service d'auth est injoignable
        }
    }

}