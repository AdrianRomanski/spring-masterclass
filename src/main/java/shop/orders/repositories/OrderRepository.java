package shop.orders.repositories;

import shop.orders.Order;

import java.util.Optional;


public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    void update(Order order);


}
