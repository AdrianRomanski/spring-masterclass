package shop.products;


import org.springframework.stereotype.Repository;
import shop.common.PagedResult;


public interface ProductRepository {

    Product save(Product product);

    PagedResult<Product> findAll(int pageNumber, int pageSize);
}
