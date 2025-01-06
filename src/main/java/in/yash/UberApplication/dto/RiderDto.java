package in.yash.UberApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {
    private Long rideId;
    private UserDto user;
    private Double rating;
}
