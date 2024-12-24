package in.yash.UberApplication.strategies;

import in.yash.UberApplication.dto.RideRequestDto;

public interface RideFairCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
