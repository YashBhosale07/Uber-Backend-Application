package in.yash.UberApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_rating_rider",columnList = "rider_id"),
        @Index(name = "idx_rating_driver",columnList = "driver_id")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Integer driverRating;

    private Integer riderRating;

    private Boolean riderRatingStatus=false;

    private Boolean driverRatingStatus=false;

}
