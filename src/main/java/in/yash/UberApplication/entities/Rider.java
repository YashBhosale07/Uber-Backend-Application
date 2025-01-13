    package in.yash.UberApplication.entities;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Setter
    @Getter
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
