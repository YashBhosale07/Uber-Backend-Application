package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.dto.RideRequestDto;
import in.yash.UberApplication.strategies.RideFairCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFairDefaultFareCalculationStrategy implements RideFairCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
