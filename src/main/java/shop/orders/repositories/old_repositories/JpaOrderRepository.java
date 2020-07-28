package shop.orders.repositories.old_repositories;

import lombok.Setter;
import shop.orders.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

//@Repository("jpaOrderRepository")
public class JpaOrderRepository implements OrderRepository{

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        System.out.println("Jpa Order Repository");
        entityManager.persist(order);
        entityManager.flush();
        entityManager.refresh(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = entityManager.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public void update(Order order) {
        if(entityManager.find(Order.class, order.getId()) != null) {
            entityManager.merge(order);
        }
    }
}
