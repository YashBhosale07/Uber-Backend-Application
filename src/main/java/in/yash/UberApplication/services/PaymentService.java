package in.yash.UberApplication.services;

import in.yash.UberApplication.entities.Payment;
import in.yash.UberApplication.entities.Ride;
import in.yash.UberApplication.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);



}
