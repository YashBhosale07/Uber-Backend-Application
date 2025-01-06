package in.yash.UberApplication.dto;

import lombok.Data;

import java.util.List;
@Data
public class WalletDto {

    private Long id;

    private UserDto userDto;

    private Double balance;

    private List<WalletTranscationDto> transcations;
}