package in.yash.UberApplication.services;

import in.yash.UberApplication.dto.DriverDto;
import in.yash.UberApplication.dto.RatingDto;
import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RatingDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getMyAllRides(PageRequest pageRequest);

    void updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
