package shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.Instant;
import static shop.payments.PaymentStatus.*;

@Log
@RequiredArgsConstructor
public class FakePaymentService {

    private static final String LOG_FORMAT = "A new payment of %s has been initialized";

    private final PaymentIdGenerator paymentIdGenerator;

    public Payment process(PaymentsRequest paymentsRequest) {
        Payment payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentsRequest.getMoney())
                .timestamp(Instant.now())
                .status(STARTED)
                .build();
        log.info(createLogEntry(payment));
        return payment;
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getMoney());
    }
}
