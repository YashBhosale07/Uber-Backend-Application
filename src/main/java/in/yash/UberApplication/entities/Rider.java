    package in.yash.UberApplication.entities;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Setter
    @Getter
    @NoArgsConstructor
    public class Rider {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Double rating;
        private Long totalRatingReceived;

        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;

        public Rider(Long id, Double rating, User user,Long totalRatingReceived) {
            this.id = id;
            this.rating = rating;
            this.user = user;
            this.totalRatingReceived=totalRatingReceived;
        }
    }
