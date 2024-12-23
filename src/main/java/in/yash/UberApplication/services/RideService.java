package in.yash.UberApplication.services;


import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDriver(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest);

    Page<Ride>getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

}

