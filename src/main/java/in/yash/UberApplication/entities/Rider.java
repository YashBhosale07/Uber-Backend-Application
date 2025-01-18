    package in.yash.UberApplication.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Rider {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Double rating=0.0;

        @OneToOne
        @JoinColumn(name = "user_id")
        private User user;

    }
