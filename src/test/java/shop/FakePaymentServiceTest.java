package shop;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import shop.payments.model.LocalMoney;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.repositories.PaymentRepository;
import shop.payments.services.payment_id_generator.PaymentIdGenerator;
import shop.payments.services.payment_service.FakePaymentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static shop.payments.model.PaymentStatus.STARTED;

@ExtendWith(MockitoExtension.class)
public class FakePaymentServiceTest {

    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder()
            .money(MONEY)
            .build();

    @Mock
    private PaymentIdGenerator paymentIdGenerator;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    private Payment payment;

    @BeforeEach
    void setUp() {
        when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);

        when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());

        FakePaymentService fakePaymentService = new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);

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

    @Test
    @DisplayName("Should save created payment")
    void shouldSaveCreatedPayment() {
        verify(paymentRepository, times(1)).save(payment);
    }
}
