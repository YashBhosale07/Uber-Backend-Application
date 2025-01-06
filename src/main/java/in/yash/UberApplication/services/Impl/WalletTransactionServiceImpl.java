package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.WalletTranscation;
import in.yash.UberApplication.repositories.WalletTransactionRepository;
import in.yash.UberApplication.services.WalletTranscationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTranscationService {

    private final WalletTransactionRepository walletTransactionRepository;

    private final ModelMapper modelMapper;


    @Override
    public void createNewWalletTrascation(WalletTranscation walletTranscation) {
        walletTransactionRepository.save(walletTranscation);
    }
}
