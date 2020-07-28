package shop.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import shop.common.PagedResult;
import shop.orders.model.Order;
import shop.orders.service.OrderService;
import shop.payments.model.Payment;
import shop.payments.model.PaymentRequest;
import shop.payments.services.payment_service.PaymentService;
import shop.products.model.Product;
import shop.products.service.ProductService;

import javax.transaction.Transactional;

@Service
@Transactional
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class ShopService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ProductService productService;

    public Product addProduct(Product product) {
        return productService.add(product);
    }

    public PagedResult<Product> getProducts(int pageNumber, int pageSize) {
        return productService.getAll(pageNumber, pageSize);
    }

    public Order placeOrder(Order order) {
        return orderService.add(order);
    }

    public Payment payForOrder(long orderId) {
        var order = orderService.getBy(orderId);
        var paymentRequest = PaymentRequest.builder()
                .money(order.getTotalPrice())
                .build();
        var payment = paymentService.process(paymentRequest);
        order.setPayment(payment);
        orderService.update(order);
        return payment;
    }
}
