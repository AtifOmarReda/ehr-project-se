package project.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> handleAuthFailed(AuthenticationFailedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "BAD_CREDENTIALS", "message", ex.getMessage(), "timestamp", Instant.now()));
    }

    @ExceptionHandler(AlreadyAuthenticatedException.class)
    public ResponseEntity<?> handleAlreadyAuthenticated(AlreadyAuthenticatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "ALREADY_AUTHENTICATED", "message", ex.getMessage(), "timestamp", Instant.now()));
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<?> handleInvalidRefreshToken(InvalidRefreshTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "INVALID_REFRESH_TOKEN", "message", ex.getMessage(), "timestamp", Instant.now()));
    }

}