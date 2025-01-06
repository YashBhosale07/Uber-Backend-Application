package in.yash.UberApplication.exceptions;

public class DriverNotAvailableForRideException extends RuntimeException {

    public DriverNotAvailableForRideException() {
    }

    public DriverNotAvailableForRideException(String message) {
        super(message);
    }
}
