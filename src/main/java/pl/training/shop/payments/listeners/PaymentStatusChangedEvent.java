package pl.training.shop.payments.listeners;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.training.shop.payments.model.Payment;

public class PaymentStatusChangedEvent extends ApplicationEvent {

    @Getter
    private final Payment payment;

    public PaymentStatusChangedEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }

}
