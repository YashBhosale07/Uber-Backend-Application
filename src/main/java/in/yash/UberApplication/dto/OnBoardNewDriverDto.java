package in.yash.UberApplication.dto;

import lombok.Data;

@Data
public class OnBoardNewDriverDto {
    private Long userId;
    private String mobile;
    private String address;
    private String vehicleNumber;
    private String drivingLicenceNumber;
    private PointDto currentLocation;
}
