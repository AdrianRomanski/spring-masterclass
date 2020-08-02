package pl.training.shop.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.shop.payments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
