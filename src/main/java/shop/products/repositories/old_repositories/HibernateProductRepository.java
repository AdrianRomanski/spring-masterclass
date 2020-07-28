package shop.products.repositories.old_repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import shop.common.PagedResult;
import shop.products.model.Product;

import java.util.List;

@RequiredArgsConstructor
//@Repository("hibernateProductRepository")
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(product);
        product.setId(id);
        return product;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        List<Product> products = sessionFactory.getCurrentSession()
                .createNamedQuery(Product.SELECT_PRODUCTS, Product.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PagedResult<>(products, pageNumber, -1);
    }
}
