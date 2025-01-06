package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.enums.PaymentMethod;
import in.yash.UberApplication.strategies.Impl.CashPaymentStrategy;
import in.yash.UberApplication.strategies.Impl.WalletPaymentStrategy;
import in.yash.UberApplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        if(paymentMethod==PaymentMethod.CASH){
            return cashPaymentStrategy;
        }
        return walletPaymentStrategy;
    }

}
