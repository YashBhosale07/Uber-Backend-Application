package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return null;
    }
}
