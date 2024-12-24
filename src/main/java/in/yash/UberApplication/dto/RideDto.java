package in.yash.UberApplication.dto;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.enums.PaymentMethod;
import in.yash.UberApplication.entities.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private Long id;

    private Point pickUpLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private Driver driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fair;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String otp;
}
