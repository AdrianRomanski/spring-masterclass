package shop.payments.services.payment_service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import shop.events.PaymentStatusChangedEvent;
import shop.payments.aspect.LogPayments;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.repositories.PaymentRepository;
import shop.payments.services.payment_id_generator.IdGenerator;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;
import shop.common.profiler.ExecutionTime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;

import static shop.payments.model.PaymentStatus.STARTED;

@Log
@Service("paymentService")
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository jpaPaymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public FakePaymentService(@IdGenerator("uuid") PaymentIdGenerator paymentIdGenerator,
                              PaymentRepository jpaPaymentRepository, ApplicationEventPublisher eventPublisher) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.eventPublisher = eventPublisher;
    }


    @Override
    @LogPayments
    @ExecutionTime
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(STARTED)
                .build();
        eventPublisher.publishEvent(new PaymentStatusChangedEvent(this, payment));
//        throw new RuntimeException("Testing Aspect");
        return jpaPaymentRepository.save(payment);
    }

    @PostConstruct
    public void init() {
        log.info("PaymentService initialized");
    }

    @PreDestroy
    public void destroy() {
        log.info("PaymentService is going down");
    }
}
