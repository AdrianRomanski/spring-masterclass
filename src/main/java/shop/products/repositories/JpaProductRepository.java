package shop.products.repositories;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import shop.common.PagedResult;
import shop.products.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("jpaProductRepository")
public class JpaProductRepository implements ProductRepository{

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        System.out.println("Jpa Product Repository");
        entityManager.persist(product);
        entityManager.flush();
        entityManager.refresh(product);
        return product;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        List<Product> products = entityManager
                .createNamedQuery(Product.SELECT_PRODUCTS, Product.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PagedResult<>(products, pageNumber, -1);
    }
}
