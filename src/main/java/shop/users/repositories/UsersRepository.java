package shop.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.users.model.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
    Page<User> findByLastNameContaining(String lastNameFragment, Pageable pageable);
}
