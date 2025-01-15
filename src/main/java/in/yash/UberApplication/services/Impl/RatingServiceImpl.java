package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Rating;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.exceptions.RideException;
import in.yash.UberApplication.repositories.DriverRepository;
import in.yash.UberApplication.repositories.RatingRepository;
import in.yash.UberApplication.repositories.RiderRepository;
import in.yash.UberApplication.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final DriverRepository driverRepository;

    private final RiderRepository riderRepository;

    @Override
    public Double rateDriver(Ride ride, Integer rating) {
        Rating ratingObj =ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("Ride not found for rating "));
        if(ratingObj.getDriverRatingStatus()){
            throw new RideException("Driver has been already rated for this ride");
        }
        ratingObj.setDriverRatingStatus(true);
        ratingObj.setDriverRating(rating);
        Driver driver = ride.getDriver();
        double newRating = ratingRepository.findByDriver(driver).stream().mapToDouble(Rating::getDriverRating).average().orElse(0.0);
        driver.setRating(newRating);
        ratingObj.setDriverRating(rating);
        driverRepository.save(driver);
        ratingRepository.save(ratingObj);
        return newRating;
    }

    @Override
    @Transactional
    public Double rateRider(Ride ride, Integer rating) {
        Rating byRide = ratingRepository.findByRide(ride).orElseThrow(()->
                new ResourceNotFoundException("Ride not found for rating"));
        if(byRide.getRiderRatingStatus()){
            throw new RideException("Rider has been already rated for this ride");
        }
        byRide.setRiderRating(rating);
        Rider rider=ride.getRider();
        double newRating = ratingRepository.findByRider(rider).stream().mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);
        byRide.setRiderRatingStatus(true);
        ratingRepository.save(byRide);
        riderRepository.save(rider);
        return newRating;
    }

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
