package shop.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.payments.model.Payment;

public interface PaymentRepositoryJpa extends JpaRepository<Payment, String> {

}
