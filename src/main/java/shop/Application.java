package shop;

import lombok.extern.java.Log;
import shop.payments.FakePaymentService;
import shop.payments.LocalMoney;
import shop.payments.PaymentsRequest;

@Log
public class Application {

    public static void main(String[] args) {

        var paymentService = new FakePaymentService();
        var paymentRequest = PaymentsRequest.builder()
                .money(LocalMoney.of(1_000))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());

    }
}
