package in.yash.UberApplication.strategies;

import in.yash.UberApplication.entities.Payment;

public interface PaymentStrategy {

    Double PLATFROM_COMMISSION=0.3;
    void processPayment(Payment payment);

}
