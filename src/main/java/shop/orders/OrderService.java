package shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.exceptions.OrderNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order add(Order order) {
        return orderRepository.save(order);
    }


    public Order getBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }


    public void update(Order order) {
        orderRepository.update(order);
    }




}