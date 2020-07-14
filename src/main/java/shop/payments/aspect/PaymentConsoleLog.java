package shop.payments.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import shop.payments.model.Payment;

import java.util.Locale;

@Log
@Aspect
@Service
@RequiredArgsConstructor
public class PaymentConsoleLog {

//    private static final String LOG_FORMAT = "A new payment of %s has been initialized";

    private static final String MESSAGE_KEY = "paymentInfo";

    private final MessageSource messageSource;

    @AfterReturning(value = "@annotation(shop.payments.aspect.LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
//        return String.format(LOG_FORMAT, payment.getMoney());
        return messageSource.getMessage(MESSAGE_KEY, new String[] {payment.getMoney().toString()},
                Locale.getDefault());
    }
}
