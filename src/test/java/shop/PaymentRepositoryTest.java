package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.payments.model.Payment;
import shop.payments.repositories.PaymentRepositoryImpl;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {

    private static final String PAYMENT_ID = "1";
    private static final Payment PAYMENT  = Payment.builder()
            .id(PAYMENT_ID)
            .build();

    private final PaymentRepositoryImpl paymentRepository = new PaymentRepositoryImpl();

    @Mock
    private Map<String, Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository.setPayments(payments);
    }

    @Test
    @DisplayName("Should add payment to hash map")
    void shouldAddPaymentToHashMap() {
        paymentRepository.save(PAYMENT);
        verify(payments).put(PAYMENT_ID, PAYMENT);
    }

}
