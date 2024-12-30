package in.yash.UberApplication.advices;

import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.exceptions.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>>handleResourceNotException(ResourceNotFoundException resourceNotFoundException){
        ApiError apiError=ApiError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>>handleRuntimeConflictException(RuntimeConflictException runtimeConflictException){
        ApiError apiError=ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(runtimeConflictException.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getHttpStatus());

    }

}
