package shop.products.repositories;

import shop.products.model.Product;

import java.util.Optional;

public interface ProductRepositoryCustom {

    Optional<Product> findByDescription(String description);
}
