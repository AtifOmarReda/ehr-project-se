package Project.management.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RemoteIsDoctorService {

    private final RestTemplate restTemplate;

    @Value("${services.auth.url}")
    private String authServiceBaseUrl;

    @Value("${services.auth.endpoints.check-is-user-doctor-endpoint}")
    private String isDoctorEndpoint;

    public RemoteIsDoctorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isUserDoctor(Long userId) {
        try {
            String fullUrl = UriComponentsBuilder.fromHttpUrl(authServiceBaseUrl).path(isDoctorEndpoint).pathSegment(userId.toString()).toUriString();
            Boolean isUserDoctor = restTemplate.getForObject(fullUrl, Boolean.class);
            return Boolean.TRUE.equals(isUserDoctor);
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification du rôle docteur pour l'ID " + userId + ": " + e.getMessage());
            return false;
        }
    }

}