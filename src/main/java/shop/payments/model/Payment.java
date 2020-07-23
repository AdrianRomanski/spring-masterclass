package shop.payments.model;

import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;
import org.javamoney.moneta.FastMoney;
import shop.common.FastMoneyUserType;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "payments", indexes = @Index(name = "payment_status", columnList = "status"))
@TypeDef(name = "fastMoney", typeClass = FastMoneyUserType.class, defaultForType = FastMoney.class)
public class Payment {

    @Id
    private String id;
    @Columns(columns = {
            @Column(name = "currency", length = 3),
            @Column(name = "value", length = 15)
    })
    private FastMoney money;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
