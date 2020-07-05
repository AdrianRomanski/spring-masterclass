package shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
@Builder
public class PaymentsRequest {

    Long id;
    FastMoney money;
}
