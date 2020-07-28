package shop.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.products.model.Product;
import shop.products.model.ProductType;

import java.util.List;

public interface ProductRepositoryJpa extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    List<Product> findByNameContaining(String name);

    @Query("select p from Product p where p.type = :type")
    List<Product> findByType(@Param("type") ProductType type);
}
