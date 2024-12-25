package in.yash.UberApplication.repositories;

import in.yash.UberApplication.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query("SELECT d.*, ST_DISTANCE(d.current_location, :pickUpLocation) AS distance " +
            "FROM DRIVERS AS d " +
            "WHERE available = true " +
            "AND ST_DWITHIN(d.current_location, :pickUpLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10")
    List<Driver> findTenNearestDriver(Point pickUpLocation);

}
