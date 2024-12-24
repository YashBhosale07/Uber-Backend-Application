package in.yash.UberApplication.strategies;

import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
