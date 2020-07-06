package shop;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.*;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.payments.model.LocalMoney;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.model.PaymentStatus;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;
import shop.payments.services.payment_service.FakePaymentService;

import static org.junit.jupiter.api.Assertions.*;
import static shop.payments.model.PaymentStatus.*;

@ExtendWith(MockitoExtension.class)
public class FakePaymentServiceTest {

    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    @Mock
    private PaymentIdGenerator paymentIdGenerator;

    private Payment payment;

    @BeforeEach
    void setUp() {
        Mockito.when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);

        FakePaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator);

        payment = fakePaymentService.process(PAYMENT_REQUEST);
    }

    @Test
    @DisplayName("Should assign generated id to created payment")
    void shouldAssignGeneratedIdToCreatedPayment() {
        assertEquals(PAYMENT_ID, payment.getId());
    }

    @Test
    @DisplayName("Should assign money from payment request to created payment")
    void shouldAssignMoneyFromPaymentRequestToCreatedPayment() {
        assertEquals(MONEY, payment.getMoney());
    }

    @Test
    @DisplayName("Should assign timestamp to created payment")
    void shouldAssignTimestampToCreatedPayment() {
        assertNotNull(payment.getTimestamp());
    }

    @Test
    @DisplayName("Should Assign STARTED status to created payment")
    void shouldAssignStartedStatusToCreatedPayment() {
        assertEquals(STARTED, payment.getStatus());
    }
}
