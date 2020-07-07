package shop.payments.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import shop.payments.model.Payment;

@Log
@Aspect
//@Service
public class PaymentConsoleLog {

    private static final String LOG_FORMAT = "A new payment of %s has been initialized";

    @AfterReturning(value = "@annotation(shop.payments.aspect.LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return String.format(LOG_FORMAT, payment.getMoney());
    }
}
