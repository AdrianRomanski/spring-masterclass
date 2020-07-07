package shop.payments.services.payment_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import shop.payments.aspect.LogPayments;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.repositories.PaymentRepository;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;

import java.time.Instant;

import static shop.payments.model.PaymentStatus.STARTED;

@Log
//@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Service("paymentService")
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

//    @Autowired
//    public FakePaymentService(@IdGenerator("uuid") PaymentIdGenerator paymentIdGenerator,
//                              PaymentRepository paymentRepository) {
//        this.paymentIdGenerator = paymentIdGenerator;
//        this.paymentRepository = paymentRepository;
//    }


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

//    @PostConstruct
    public void init() {
        log.info("PaymentService initialized");
    }

//    @PreDestroy
    public void destroy() {
        log.info("PaymentService is going down");
    }
}
