package in.yash.UberApplication.advices;

import in.yash.UberApplication.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotException(ResourceNotFoundException resourceNotFoundException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(RuntimeConflictException runtimeConflictException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(runtimeConflictException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RideRequestNotAcceptedException.class)
    public ResponseEntity<ApiResponse<?>> handleRideRequestNotAcceptedException(RideRequestNotAcceptedException rideRequestNotAcceptedException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .message(rideRequestNotAcceptedException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(DriverNotAvailableForRideException.class)
    public ResponseEntity<ApiResponse<?>> handleDriverNotAvailableForRideException(DriverNotAvailableForRideException driverNotAvailableForRideException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .message(driverNotAvailableForRideException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(OtpInvalidException.class)
    public ResponseEntity<ApiResponse<?>> handleOtpInvalidException(OtpInvalidException otpInvalidException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(otpInvalidException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RideException.class)
    public ResponseEntity<ApiResponse<?>> handleRideException(RideException rideException) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(rideException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getHttpStatus());

    }

}
