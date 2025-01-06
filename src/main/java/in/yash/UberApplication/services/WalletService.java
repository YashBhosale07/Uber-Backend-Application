package in.yash.UberApplication.services;

import in.yash.UberApplication.dto.RideDto;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.User;
import in.yash.UberApplication.entities.Wallet;
import in.yash.UberApplication.entities.enums.TranscationMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TranscationMethod TranscationMethod);

    Wallet deductMoenyFromWallet(User user,Double amount,String transactionId, Ride ride, TranscationMethod TranscationMethod);
    void withDrawMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
