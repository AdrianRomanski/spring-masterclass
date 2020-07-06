package shop.payments.services.payment_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;

@Log
@RequiredArgsConstructor
public class LoggingPaymentService implements PaymentService {

    private static final String LOG_FORMAT = "A new payment of %s has been initialized";

    private final FakePaymentService paymentService;

    @Override
    public Payment process(PaymentRequest paymentsRequest) {
        var payment = paymentService.process(paymentsRequest);
        log.info(createLogEntry(payment));
        return payment;
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getMoney());
    }


}
