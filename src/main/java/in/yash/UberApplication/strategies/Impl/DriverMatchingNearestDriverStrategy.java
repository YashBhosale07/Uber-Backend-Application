package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.repositories.DriverRepository;
import in.yash.UberApplication.strategies.DriverMatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
