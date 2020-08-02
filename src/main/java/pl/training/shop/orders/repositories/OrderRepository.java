package pl.training.shop.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.training.shop.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
