package shop.products.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.common.PagedResult;
import shop.products.model.Product;
import shop.products.repositories.ProductRepository;

import java.util.List;

@Log
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private static Long id = 0L;

//    @Retry
    @Override
    @CacheEvict("products")
    public Product add(Product product) {
        product.setId(++id);
        return productRepository.save(product);
    }

    @Override
    @Cacheable("products")
    public List<Product> getByName(String name) {
        System.out.println("Reading products from database");
        return productRepository.findByNameContaining(name);
    }

    @Override
    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
