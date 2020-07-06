package shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import shop.payments.model.LocalMoney;
import shop.payments.model.PaymentRequest;
import shop.payments.services.payment_service.PaymentService;

@Log
public class Application {

    private static final String BASE_PACKAGE = "shop";

    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var paymentService = applicationContext.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(LocalMoney.of(1_000))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }
    }
}
