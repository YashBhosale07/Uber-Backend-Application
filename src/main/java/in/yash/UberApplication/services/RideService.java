package in.yash.UberApplication.services;


import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

}

