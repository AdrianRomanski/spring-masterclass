package shop.products.repositories;


import shop.common.PagedResult;
import shop.products.Product;


public interface ProductRepository {

    Product save(Product product);

    PagedResult<Product> findAll(int pageNumber, int pageSize);
}
