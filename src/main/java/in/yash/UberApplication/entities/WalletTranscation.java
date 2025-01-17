package in.yash.UberApplication.entities;

import in.yash.UberApplication.entities.enums.TransactionType;
import in.yash.UberApplication.entities.enums.TranscationMethod;
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
@Table(indexes = {
        @Index(name = "idx_wallet_transcation_wallet",columnList = "wallet_id"),
        @Index(name = "idx_wallet_transcation_ride",columnList = "ride_id")
})
public class WalletTranscation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TranscationMethod transcationMethod;

    @ManyToOne
    private Ride ride;

    private String transcationId;

    @CreationTimestamp
    private LocalDateTime timeStamp;

    @ManyToOne
    private Wallet wallet;
}