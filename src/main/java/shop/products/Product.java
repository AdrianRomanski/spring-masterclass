package shop.products;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;

@Data
@Entity
@Builder
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", indexes = @Index(name = "payment_type", columnList = "type"))
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value", length = 15)
    })
    private FastMoney price;
    @Enumerated(EnumType.STRING)
    private ProductType type;

}
