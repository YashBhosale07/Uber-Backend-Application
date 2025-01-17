package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.Wallet;
import in.yash.UberApplication.entities.WalletTranscation;
import in.yash.UberApplication.entities.enums.TransactionType;
import in.yash.UberApplication.entities.enums.TranscationMethod;
import in.yash.UberApplication.exceptions.ResourceNotFoundException;
import in.yash.UberApplication.repositories.WalletRepository;
import in.yash.UberApplication.repositories.WalletTransactionRepository;
import in.yash.UberApplication.services.WalletService;
import in.yash.UberApplication.services.WalletTranscationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionRepository walletTransactionRepository;
    private final WalletTranscationService walletTranscationService;

    @Override
    @Transactional
    public void addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TranscationMethod transcationMethod) {
        System.out.println(user.getId());
        Wallet wallet=findWalletByUser(user);

        Double alreadyExitsAmount=wallet.getBalance();
        wallet.setBalance(amount+alreadyExitsAmount);
        WalletTranscation walletTranscation=WalletTranscation.builder()
                .transcationId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transcationMethod(transcationMethod)
                .amount(amount)
                .build();
        walletTranscationService.createNewWalletTrascation(walletTranscation);
        walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public void deductMoenyFromWallet(User user, Double amount, String transactionId, Ride ride, TranscationMethod transcationMethod) {
        Wallet wallet=findWalletByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        WalletTranscation walletTranscation=WalletTranscation.builder()
                .transcationId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transcationMethod(transcationMethod)
                .amount(amount)
                .build();
        wallet.getTranscations().add(walletTranscation) ;
        walletRepository.save(wallet);
    }

    @Override
    public void withDrawMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found with id "+walletId));
    }

    @Override
    public void createNewWallet(User user) {
        Wallet wallet=new Wallet();
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found for user with id "+user.getId()));
    }
}
