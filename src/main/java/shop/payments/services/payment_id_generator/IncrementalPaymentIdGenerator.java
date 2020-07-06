package shop.payments.services.payment_id_generator;

import lombok.Setter;
import org.springframework.stereotype.Component;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;

@Component
public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {

    private static final String ID_FORMAT = "%010d";

    @Setter
    private long index;

    @Override
    public String getNext() {
        return String.format(ID_FORMAT, ++index);
    }
}
