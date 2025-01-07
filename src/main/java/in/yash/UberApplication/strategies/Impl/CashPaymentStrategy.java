package in.yash.UberApplication.strategies.Impl;

import in.yash.UberApplication.entities.Driver;
import in.yash.UberApplication.entities.Payment;
import in.yash.UberApplication.entities.enums.PaymentStatus;
import in.yash.UberApplication.entities.enums.TranscationMethod;
import in.yash.UberApplication.repositories.PaymentRepository;
import in.yash.UberApplication.services.PaymentService;
import in.yash.UberApplication.services.WalletService;
import in.yash.UberApplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();
        double platformCommission=payment.getAmount()*PLATFROM_COMMISSION;
        walletService.deductMoenyFromWallet(driver.getUser(),platformCommission,null,payment.getRide(), TranscationMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
