package in.yash.UberApplication.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Rider(Long id, Double rating, User user) {
        this.id = id;
        this.rating = rating;
        this.user = user;
    }
}
