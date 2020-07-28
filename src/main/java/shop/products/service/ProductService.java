package shop.products.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import shop.common.PagedResult;
import shop.products.model.Product;
import shop.products.repositories.ProductRepositoryJpa;

@Service
public class ProductService {

    private final ProductRepositoryJpa productRepository;

    public ProductService(ProductRepositoryJpa productRepository) {
        this.productRepository = productRepository;
    }

    //    @Retry
    public Product add(Product product) {
        return productRepository.save(product);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
