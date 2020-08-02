package pl.training.shop.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.orders.services.OrderService;
import pl.training.shop.orders.repositories.OrderRepository;

@Configuration
public class OrdersConfiguration {

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);                                         
    }

}
