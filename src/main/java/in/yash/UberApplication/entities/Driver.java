package in.yash.UberApplication.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Setter
@Getter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Boolean available;
    private String vehicleNumber;
    private String drivingLicenceNumber;
    private String mobile;
    private String address;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;

}

