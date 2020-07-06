package shop.payments.services.payment_service;

import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);
}
