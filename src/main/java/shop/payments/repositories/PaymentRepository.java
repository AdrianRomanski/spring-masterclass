package shop.payments.repositories;

import shop.payments.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
