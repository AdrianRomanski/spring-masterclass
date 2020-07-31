package shop.products.service;

import shop.common.PagedResult;
import shop.products.model.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);
    List<Product> getByName(String name);
    PagedResult<Product> getAll(int pageNumber, int pageSize);


}
