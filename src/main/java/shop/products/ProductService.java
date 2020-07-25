package shop.products;

import org.springframework.stereotype.Service;
import shop.common.PagedResult;

@Service
public class ProductService {

    private final ProductRepository hibernateProductRepository;

    public ProductService(ProductRepository hibernateProductRepository) {
        this.hibernateProductRepository = hibernateProductRepository;
    }

    //    @Retry
    public Product add(Product product) {
        return hibernateProductRepository.save(product);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        return hibernateProductRepository.findAll(pageNumber, pageSize);
    }
}
