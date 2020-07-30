package shop.users.services;

import shop.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);

    Optional<User> getByFullName(String firstName, String lastName);

    List<User> getAll();

    Optional<User> getById(Long id);
}
