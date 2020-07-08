package shop.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import shop.payments.model.LocalMoney;
import shop.payments.model.Payment;
import shop.products.Product;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {

    private Long id;
    @NonNull
    private List<Product> products;
    private Payment payment;

    public FastMoney getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(LocalMoney.zero(), FastMoney::add);
    }

}
