package shop.orders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.common.validator.Validate;
import shop.exceptions.InvalidOrderException;
import shop.exceptions.OrderNotFoundException;
import shop.orders.model.Order;
import shop.orders.repositories.old_repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository jpaOrderRepository;


    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        return jpaOrderRepository.save(order);
    }


    public Order getBy(Long orderId) {
        return jpaOrderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }


    public void update(Order order) {
        jpaOrderRepository.update(order);
    }




}
