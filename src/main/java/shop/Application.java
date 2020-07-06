package shop;

import lombok.extern.java.Log;
import shop.payments.model.LocalMoney;
import shop.payments.model.PaymentRequest;
import shop.payments.services.payment_id_generator.IncrementalPaymentIdGenerator;
import shop.payments.services.payment_service.FakePaymentService;
import shop.payments.services.payment_service.LoggingPaymentService;

@Log
public class Application {

    public static void main(String[] args) {

        var paymentIdGenerator = new IncrementalPaymentIdGenerator();
        var fakePaymentService = new FakePaymentService(paymentIdGenerator);
        var paymentService = new LoggingPaymentService(fakePaymentService);
        var paymentRequest = PaymentRequest.builder()
                .money(LocalMoney.of(1_000))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());
    }
}
