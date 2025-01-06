package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Payment;
import in.yash.UberApplication.entities.Rider;
import in.yash.UberApplication.entities.enums.PaymentMethod;
import in.yash.UberApplication.entities.enums.PaymentStatus;
import in.yash.UberApplication.entities.enums.TranscationMethod;
import in.yash.UberApplication.services.PaymentService;
import in.yash.UberApplication.services.WalletService;
import in.yash.UberApplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();
        Rider rider=payment.getRide().getRider();
        walletService.deductMoenyFromWallet(rider.getUser(),payment.getAmount(),null,payment.getRide(), TranscationMethod.RIDE);
        double driversCut=payment.getAmount()*(1-PLATFROM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(),driversCut,null,payment.getRide(),TranscationMethod.RIDE);
        paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
    }
}
