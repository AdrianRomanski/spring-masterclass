package shop.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import shop.payments.model.Payment;

public class PaymentStatusChangedEvent extends ApplicationEvent {

    @Getter
    private final Payment payment;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PaymentStatusChangedEvent(Object source, Payment payment) {
        super(source);
        this.payment = payment;
    }
}
