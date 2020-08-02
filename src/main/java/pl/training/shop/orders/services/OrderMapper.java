package pl.training.shop.orders.services;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.shop.common.web.FastMoneyMapper;
import pl.training.shop.common.web.IdTransferObject;
import pl.training.shop.orders.model.Order;
import pl.training.shop.orders.model.OrderTransferObject;
import pl.training.shop.products.model.Product;
import pl.training.shop.products.services.ProductMapper;
import pl.training.shop.products.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {FastMoneyMapper.class, ProductMapper.class})
public abstract class OrderMapper {

    @Autowired
    private ProductService productService;

    public Order toOrder(OrderTransferObject orderTransferObject) {
        List<Product> products = orderTransferObject.getProducts().stream()
                .map(IdTransferObject::getId)
                .map(productService::getById)
                .collect(Collectors.toList());
        return new Order(products);
    }

}
