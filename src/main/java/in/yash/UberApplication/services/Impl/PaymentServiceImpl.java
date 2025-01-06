package in.yash.UberApplication.services.Impl;

import in.yash.UberApplication.entities.Payment;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.enums.PaymentStatus;
import in.yash.UberApplication.repositories.PaymentRepository;
import in.yash.UberApplication.services.PaymentService;
import in.yash.UberApplication.services.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Payment payment) {
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment=Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
