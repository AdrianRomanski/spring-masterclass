package shop.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.orders.model.Order;

public interface OrderRepositoryJpa extends JpaRepository<Order, Long> {

}
