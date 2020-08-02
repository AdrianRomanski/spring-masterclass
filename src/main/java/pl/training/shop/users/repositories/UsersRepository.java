package pl.training.shop.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.shop.users.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {

    Page<User> findByLastNameContaining(String lastNameFragment, Pageable pageable);

}
