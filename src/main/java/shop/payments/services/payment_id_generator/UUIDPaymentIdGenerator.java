package shop.payments.services.payment_id_generator;

import java.util.UUID;


@IdGenerator("uuid")
public class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
