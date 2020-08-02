package shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.training.shop.payments.id_generators.IncrementalPaymentIdGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncrementalPaymentGeneratorTest {

    private static final String ID_FORMAT = "\\w{10}";

    private final IncrementalPaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator();


    @Test
    @DisplayName("Should generate valid id")
    void shouldGenerateValidID() {
        String id = paymentIdGenerator.getNext();
        assertTrue(id.matches(ID_FORMAT));
    }


    @Test
    @DisplayName("Should generate valid id by incrementing value of previous one")
    void shouldGenerateIdByIncrementingValueOfPreviousOne() {
        long firstIdValue = Long.parseLong(paymentIdGenerator.getNext());
        long secondIdValue = Long.parseLong(paymentIdGenerator.getNext());
        assertEquals(firstIdValue + 1, secondIdValue);
    }
}
