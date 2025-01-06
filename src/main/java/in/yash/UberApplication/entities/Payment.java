package in.yash.UberApplication.entities;


import in.yash.UberApplication.entities.enums.PaymentMethod;
import in.yash.UberApplication.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}

