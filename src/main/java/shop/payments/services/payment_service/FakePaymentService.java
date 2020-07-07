package shop.payments.services.payment_service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import shop.payments.aspect.LogPayments;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.repositories.PaymentRepository;
import shop.payments.services.payment_id_generator.IdGenerator;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Instant;

import static shop.payments.model.PaymentStatus.STARTED;

@Log
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Service("paymentService")
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @Autowired
    public FakePaymentService(@IdGenerator("uuid") PaymentIdGenerator paymentIdGenerator,
                              PaymentRepository paymentRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentRepository = paymentRepository;
    }


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

    @PostConstruct
    public void init() {
        log.info("PaymentService initialized");
    }

    @PreDestroy
    public void destroy() {
        log.info("PaymentService is going down");
    }
}
