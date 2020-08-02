package pl.training.shop.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.products.services.ProductService;
import pl.training.shop.products.repositories.ProductRepository;

@Configuration
public class ProductsConfiguration {

    @Bean
    public ProductService productsService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}
