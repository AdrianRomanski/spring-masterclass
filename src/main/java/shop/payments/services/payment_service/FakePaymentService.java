package shop.payments.services.payment_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.payments.aspect.LogPayments;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;


import java.time.Instant;
import static shop.payments.model.PaymentStatus.*;


@Component
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;

    @Override
    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(STARTED)
                .build();
    }
}
