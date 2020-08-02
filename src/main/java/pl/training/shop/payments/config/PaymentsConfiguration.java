package pl.training.shop.payments.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.payments.services.FakePaymentService;
import pl.training.shop.payments.listeners.PaymentStatusChangeListener;
import pl.training.shop.payments.id_generators.IncrementalPaymentIdGenerator;
import pl.training.shop.payments.id_generators.PaymentIdGenerator;
import pl.training.shop.payments.log.PaymentConsoleLogger;
import pl.training.shop.payments.repositories.PaymentRepository;
import pl.training.shop.payments.services.PaymentService;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository, ApplicationEventPublisher eventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger(MessageSource messageSource) {
        return new PaymentConsoleLogger(messageSource);
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }

}
