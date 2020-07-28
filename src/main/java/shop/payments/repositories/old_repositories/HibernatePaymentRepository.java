package shop.payments.repositories.old_repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import shop.payments.model.Payment;

@RequiredArgsConstructor
//@Repository("hibernatePaymentRepository")
public class HibernatePaymentRepository implements PaymentRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Payment save(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(payment);
        payment.setId(id);
        return payment;
    }
}
