package in.yash.UberApplication.exceptions;

public class InvalidAccessTokenException extends RuntimeException{

    public InvalidAccessTokenException() {
    }

    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
