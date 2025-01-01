package in.yash.UberApplication.strategies;

import in.yash.UberApplication.entities.RideRequest;

public interface RideFairCalculationStrategy {

    double RIDE_FARE_MULTIPLIER=10;
    double calculateFare(RideRequest rideRequest);
}
