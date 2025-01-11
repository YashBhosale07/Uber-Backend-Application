package in.yash.UberApplication.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private Boolean available;

    private String vehicleId;

    private Long totalRatingReceived=0L;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;

}

