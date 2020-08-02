package pl.training.shop.payments.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.profiler.ExecutionTime;
import pl.training.shop.payments.listeners.PaymentStatusChangedEvent;
import pl.training.shop.payments.id_generators.PaymentIdGenerator;
import pl.training.shop.payments.log.LogPayments;
import pl.training.shop.payments.model.Payment;
import pl.training.shop.payments.model.PaymentRequest;
import pl.training.shop.payments.model.PaymentStatus;
import pl.training.shop.payments.repositories.PaymentRepository;

import java.time.Instant;

@Transactional
@Log
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @ExecutionTime
    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        eventPublisher.publishEvent(new PaymentStatusChangedEvent(this, payment));
        return paymentRepository.save(payment);
    }

}
