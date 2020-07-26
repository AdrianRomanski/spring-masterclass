package shop.products;

import org.springframework.stereotype.Service;
import shop.common.PagedResult;
import shop.products.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository jpaProductRepository;

    public ProductService(ProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    //    @Retry
    public Product add(Product product) {
        return jpaProductRepository.save(product);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        return jpaProductRepository.findAll(pageNumber, pageSize);
    }
}
