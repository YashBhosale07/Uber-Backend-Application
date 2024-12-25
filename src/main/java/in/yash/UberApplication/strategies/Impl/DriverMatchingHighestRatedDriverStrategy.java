package in.yash.UberApplication.strategies.Impl;


import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return null;
    }
}
