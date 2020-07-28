package shop.orders.repositories.old_repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import shop.orders.model.Order;

import java.util.Optional;

@RequiredArgsConstructor
//@Repository("hibernateOrderRepository")
public class HibernateOrderRepository implements OrderRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Order save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(order);
        order.setId(id);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        if(session.load(Order.class, order.getId()) != null) {
            session.update(order);
        }
    }
}
