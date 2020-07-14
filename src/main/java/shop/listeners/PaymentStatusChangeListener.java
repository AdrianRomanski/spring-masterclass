package shop.listeners;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import shop.events.PaymentStatusChangedEvent;

@Log
@Service
public class PaymentStatusChangeListener {


    @EventListener
    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status " + statusChangedEvent.getPayment());
    }
}
