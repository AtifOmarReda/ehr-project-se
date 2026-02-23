package Project.management.services;

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

    public RemoteBlacklistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isTokenBlacklisted(String token) {
        try {
            String fullUrl = UriComponentsBuilder.fromHttpUrl(authServiceBaseUrl).path(blacklistEndpoint).queryParam("token", token).toUriString();
            Boolean isBlacklisted = restTemplate.getForObject(fullUrl, Boolean.class);
            return Boolean.TRUE.equals(isBlacklisted);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

}