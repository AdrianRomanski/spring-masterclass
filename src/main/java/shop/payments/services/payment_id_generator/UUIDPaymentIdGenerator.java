package shop.payments.services.payment_id_generator;

import shop.payments.services.payment_id_generator.PaymentIdGenerator;

import java.util.UUID;

public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
