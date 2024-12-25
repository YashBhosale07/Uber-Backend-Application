package in.yash.UberApplication.strategies;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
