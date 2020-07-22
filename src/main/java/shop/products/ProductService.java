package shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.common.PagedResult;
import shop.common.retry.Retry;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Retry
    public Product add(Product product) {
        throw new RuntimeException();
//        return productRepository.save(product);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        return productRepository.findAll(pageNumber, pageSize);
    }
}
