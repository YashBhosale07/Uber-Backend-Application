package in.yash.UberApplication.repositories;

import in.yash.UberApplication.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(value = "SELECT d.*, ST_DISTANCE(d.current_location, :pickUpLocation) AS distance " +
            "FROM DRIVER d " +
            "WHERE d.available = true " +
            "AND ST_DWITHIN(d.current_location, :pickUpLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10",nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);

    @Query(value = "SELECT d.*, " +
            "ST_Distance(" +
            "   ST_Transform(d.current_location, 3857), " +
            "   ST_Transform(ST_SetSRID(:pickUpLocation, 4326), 3857) " +
            ") AS distance " +
            "FROM DRIVER d " +
            "WHERE d.available = true " +
            "AND ST_DWithin(" +
            "   ST_Transform(d.current_location, 3857), " +
            "   ST_Transform(ST_SetSRID(:pickUpLocation, 4326), 3857), " +
            "   15000" +
            ") " +
            "ORDER BY distance ASC, d.rating DESC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Driver> findTenNearestTopRatedDrivers(Point pickUpLocation);









}
