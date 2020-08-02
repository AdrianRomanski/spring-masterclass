package shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.training.shop.payments.id_generators.UUIDPaymentIdGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UUIDPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

    private final UUIDPaymentIdGenerator paymentIdGenerator = new UUIDPaymentIdGenerator();


    @Test
    @DisplayName("Should generate valid id")
    void shouldGenerateValid() {
        String id = paymentIdGenerator.getNext();
        assertTrue(id.matches(ID_FORMAT));
    }

}
