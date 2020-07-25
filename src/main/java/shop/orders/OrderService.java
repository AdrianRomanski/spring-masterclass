package shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.common.validator.Validate;
import shop.exceptions.InvalidOrderException;
import shop.exceptions.OrderNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository hibernateOrderRepository;


    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        return hibernateOrderRepository.save(order);
    }


    public Order getBy(Long orderId) {
        return hibernateOrderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }


    public void update(Order order) {
        hibernateOrderRepository.update(order);
    }




}
