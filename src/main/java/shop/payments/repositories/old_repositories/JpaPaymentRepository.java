package shop.payments.repositories.old_repositories;

import lombok.Setter;
import shop.payments.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository("jpaPaymentRepository")
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
