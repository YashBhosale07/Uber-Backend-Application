package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Rating;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.repositories.RatingRepository;
import in.yash.UberApplication.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public void rateDriver(Driver driver, Ride ride, Integer rating) {
        Rating ratingObj =ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("Rating not found for ride "));
        ratingObj.setDriverRating(rating);
        
    }

    @Override
    public void rateRider(Rider rider, Ride ride, Integer rating) {

    }
}
