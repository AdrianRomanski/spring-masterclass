package shop.products.repositories.old_repositories;


import shop.common.PagedResult;
import shop.products.model.Product;


public interface ProductRepository {

    Product save(Product product);

    PagedResult<Product> findAll(int pageNumber, int pageSize);
}
