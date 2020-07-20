package shop.payments.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;

import java.util.Locale;

@Log
@Aspect
//@Order(50)
@Service
@RequiredArgsConstructor
public class PaymentConsoleLog implements Ordered {

//    private static final String LOG_FORMAT = "A new payment of %s has been initialized";

    private static final String MESSAGE_KEY = "paymentInfo";

    private final MessageSource messageSource;

    @Before(value = "@annotation(LogPayments) && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @After("@annotation(LogPayments)")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterThrowing(value = "@annotation(LogPayments)", throwing = "exception")
    public void onException(Exception exception) {
        log.info("Payment exception: " + exception.getClass().getSimpleName());
    }

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
//        return String.format(LOG_FORMAT, payment.getMoney());
        return messageSource.getMessage(MESSAGE_KEY, new String[] {payment.getMoney().toString()},
                Locale.getDefault());
    }

    @Override
    public int getOrder() {
        return 50;
    }
}
