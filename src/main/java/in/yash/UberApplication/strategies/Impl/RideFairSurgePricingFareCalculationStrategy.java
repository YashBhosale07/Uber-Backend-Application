package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.services.DistanceService;
import in.yash.UberApplication.strategies.RideFairCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideFairSurgePricingFareCalculationStrategy implements RideFairCalculationStrategy {

    private static final double SURGE_FACTOR = 2;
    @Autowired
    private DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),
                rideRequest.getDropOffLocation());

        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}
