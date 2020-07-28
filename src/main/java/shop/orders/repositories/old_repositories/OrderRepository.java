package shop.orders.repositories.old_repositories;

import shop.orders.model.Order;

import java.util.Optional;


public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    void update(Order order);


}
