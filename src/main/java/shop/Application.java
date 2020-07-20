package shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.orders.Order;
import shop.payments.model.LocalMoney;
import shop.products.Product;

import java.util.List;

import static shop.products.ProductType.BOOK;
import static shop.products.ProductType.VIDEO;

@Log
public class Application {

    private static final String BASE_PACKAGE = "shop";
    private static final String CONFIG_LOCATION = "beans.xml";

    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring Masterclass")
            .description("Practical course Spring Framework")
            .type(VIDEO)
            .price(LocalMoney.of(799))
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring Guide")
            .description("Practical exercises")
            .type(BOOK)
            .price(LocalMoney.of(200))
            .build();


    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
             var shopService = applicationContext.getBean(ShopService.class);
             shopService.addProduct(VIDEO_PRODUCT);
             shopService.addProduct(BOOK_PRODUCT);
             log.info(shopService.getProducts(0, 100).toString());

             var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
//             var order = new Order(Collections.emptyList());
             shopService.placeOrder(order);
             var payment = shopService.payForOrder(order.getId());
             log.info(payment.getId());
        }
    }
}
