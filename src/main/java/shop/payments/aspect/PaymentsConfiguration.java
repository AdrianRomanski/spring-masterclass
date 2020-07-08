package shop.payments.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import shop.payments.repositories.PaymentRepository;
import shop.payments.repositories.PaymentRepositoryImpl;
import shop.payments.services.payment_id_generator.IncrementalPaymentIdGenerator;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;
import shop.payments.services.payment_id_generator.UUIDPaymentIdGenerator;
import shop.payments.services.payment_service.FakePaymentService;
import shop.payments.services.payment_service.PaymentService;

@Configuration
@EnableAspectJAutoProxy
public class PaymentsConfiguration {

    @Bean(name = "paymentIdGenerator")
    public PaymentIdGenerator incrementalPaymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new PaymentRepositoryImpl();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository);
    }

    @Bean
    public PaymentConsoleLog paymentConsoleLog() {
        return new PaymentConsoleLog();
    }

}
