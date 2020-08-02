package pl.training.shop.payments.services;

import pl.training.shop.payments.model.PaymentRequest;
import pl.training.shop.payments.model.Payment;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

}
