package in.yash.UberApplication.exceptions;

public class OtpInvalidException extends RuntimeException {
    public OtpInvalidException() {
    }

    public OtpInvalidException(String message) {
        super(message);
    }
}
