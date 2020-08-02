package pl.training.shop.products.repositories;

import pl.training.shop.products.model.Product;

import java.util.Optional;

public interface ProductRepositoryCustom {

    Optional<Product> findByDescription(String description);

}
