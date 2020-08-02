package pl.training.shop.products.services;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import pl.training.shop.common.web.FastMoneyMapper;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.products.model.Product;
import pl.training.shop.products.model.ProductTransferObject;
import pl.training.shop.products.model.ProductType;
import pl.training.shop.products.model.ProductTypeTransferObject;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface ProductMapper {

    Product toProduct(ProductTransferObject productTransferObject);

    ProductTransferObject toProductTransferObject(Product product);

    PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage);

    @ValueMapping(source = "BOOK", target = "EBOOK")
    @ValueMapping(source = "AUDIO", target = "MUSIC")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductTypeTransferObject toProductTypeTransferObject(ProductType productType);

    @InheritInverseConfiguration
    ProductType toProductType(ProductTypeTransferObject productTypeTransferObject);

}
