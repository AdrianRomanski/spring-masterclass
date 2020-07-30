package shop.products.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.common.PagedResult;
import shop.products.model.Product;
import shop.products.repositories.ProductRepositoryJpa;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepositoryJpa productRepository;

    public ProductService(ProductRepositoryJpa productRepository) {
        this.productRepository = productRepository;
    }

    //    @Retry
    @CacheEvict("products")
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> getByName(String name) {
        System.out.println("Reading products from database");
        return productRepository.findByNameContaining(name);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
