package Project.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class GatewayHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final String secret;

    // Constructeur qui reçoit le secret
    public GatewayHeaderInterceptor(String secret) {
        this.secret = secret;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("X-Gateway-Secret", secret);
        return execution.execute(request, body);
    }

}