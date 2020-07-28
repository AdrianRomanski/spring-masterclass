package shop.payments.repositories.old_repositories;

import lombok.Setter;
import shop.payments.model.Payment;

import java.util.HashMap;
import java.util.Map;

//@Repository("paymentRepository")
public class PaymentRepositoryImpl implements PaymentRepository {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
}
