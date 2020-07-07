package shop.payments.repositories;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import shop.payments.model.Payment;

import java.util.HashMap;
import java.util.Map;

@Repository("paymentRepository")
public class PaymentRepositoryImpl implements PaymentRepository {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
}
