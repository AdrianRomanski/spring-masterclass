package shop.products.service;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import shop.payments.model.LocalMoney;
import shop.products.model.Product;
import shop.products.model.ProductTransferObject;
import shop.products.model.ProductType;
import shop.products.model.ProductTypeTransferObject;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default FastMoney toFastMoney(String price) {
        if(price == null) {
            return LocalMoney.of(0);
        }
        return FastMoney.parse(price);
    }

    default String toPrice(FastMoney price) {
        if(price == null) {
            return "";
        }
        return price.toString();
    }


    ProductTransferObject toTransferObject(Product product);

    Product toProduct(ProductTransferObject productTransferObject);


    @ValueMapping(source = "BOOK", target = "EBOOK")
    @ValueMapping(source = "AUDIO", target = "MUSIC")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductTypeTransferObject toProductTypeTransferObject(ProductType productType);

    @ValueMapping(source = "EBOOK", target = "BOOK")
    @ValueMapping(source = "MUSIC", target = "AUDIO")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductType toProductType(ProductTypeTransferObject productTypeTransferObject);


}
