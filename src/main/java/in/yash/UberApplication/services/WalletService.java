package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.Wallet;
import in.yash.UberApplication.entities.enums.TranscationMethod;

public interface WalletService {

    void addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TranscationMethod TranscationMethod);

    void deductMoenyFromWallet(User user, Double amount, String transactionId, Ride ride, TranscationMethod TranscationMethod);
    void withDrawMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    void createNewWallet(User user);

    Wallet findWalletByUser(User user);

}
