package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.Rating;
import in.yash.UberApplication.entities.Ride;

public interface RatingService {
    Double rateDriver( Ride ride, Integer rating);
    Double rateRider(Ride ride, Integer rating);

    void saveRating(Rating rating);
}
