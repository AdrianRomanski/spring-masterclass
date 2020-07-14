package shop.products;


import shop.common.PagedResult;


public interface ProductRepository {

    Product save(Product product);

    PagedResult<Product> findAll(int pageNumber, int pageSize);
}
