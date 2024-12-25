package in.yash.UberApplication.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private T data;
    private ApiResponse error;

    public ApiResponse(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApiResponse error) {
        this.error = error;
    }
}
