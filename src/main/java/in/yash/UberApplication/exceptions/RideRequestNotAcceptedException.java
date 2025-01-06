package in.yash.UberApplication.exceptions;

public class RideRequestNotAcceptedException extends RuntimeException {
    public RideRequestNotAcceptedException() {
    }

    public RideRequestNotAcceptedException(String message) {
        super(message);
    }
}
