package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.entities.RideRequest;
import in.yash.UberApplication.strategies.RideFairCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFairSurgePricingFareCalculationStrategy implements RideFairCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}