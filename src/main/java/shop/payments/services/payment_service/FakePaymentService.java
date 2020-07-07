package shop.payments.services.payment_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.payments.aspect.LogPayments;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.repositories.PaymentRepository;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;


import java.time.Instant;
import static shop.payments.model.PaymentStatus.*;


@Component
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @Override
    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(STARTED)
                .build();
        return paymentRepository.save(payment);
    }
}
