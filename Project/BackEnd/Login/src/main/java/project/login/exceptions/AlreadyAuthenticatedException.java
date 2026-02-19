package project.login.exceptions;

public class AlreadyAuthenticatedException extends RuntimeException {

    public AlreadyAuthenticatedException(String message) {
        super(message);
    }

}