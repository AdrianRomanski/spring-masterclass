package shop.products.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;

@Data
@RequiredArgsConstructor
public class ProductTransferObject {

    private String name;
    private String description;
    private FastMoney price;
    private ProductTypeTransferObject productTypeTransferObject;

}
