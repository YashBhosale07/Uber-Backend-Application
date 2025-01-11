package in.yash.UberApplication.services;

import in.yash.UberApplication.dto.*;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RatingDto rateDriver(Long rideId, Double rating);

    RiderDto getMyProfile();

    Page<RideDto> getMyAllRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();


}
