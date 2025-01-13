package in.yash.UberApplication.repositories;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Rating;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating>findByDriver(Driver driver);
    Optional<Rating> findByRide(Ride ride);
}
