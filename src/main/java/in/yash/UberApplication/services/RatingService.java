package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.Rider;

public interface RatingService {
    void rateDriver(Driver driver, Ride ride, Integer rating);
    void rateRider(Rider rider,Ride ride, Integer rating);
}
