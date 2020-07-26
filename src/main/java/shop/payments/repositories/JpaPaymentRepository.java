package shop.payments.repositories;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import shop.payments.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("jpaPaymentRepository")
public class JpaPaymentRepository implements PaymentRepository{

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        System.out.println("Jpa Payment Repository");
        entityManager.persist(payment);
        entityManager.flush();
        entityManager.refresh(payment);
        return payment;
    }
}
