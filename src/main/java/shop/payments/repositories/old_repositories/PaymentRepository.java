package shop.payments.repositories.old_repositories;

import shop.payments.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
