package shop.payments.services.payment_id_generator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Component
@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
public @interface IdGenerator {

    String value() default "";
}
