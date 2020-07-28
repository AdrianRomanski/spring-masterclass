package shop.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.users.model.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
}
