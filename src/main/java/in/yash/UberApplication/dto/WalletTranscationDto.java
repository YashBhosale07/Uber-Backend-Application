package in.yash.UberApplication.dto;

import in.yash.UberApplication.entities.enums.TransactionType;
import in.yash.UberApplication.entities.enums.TranscationMethod;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class WalletTranscationDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TranscationMethod transcationMethod;

    private RideDto rideDto;

    private String transcationId;

    private LocalDateTime timeStamp;

    private WalletDto walletDto;

}
