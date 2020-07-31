package shop.users.services;

import shop.common.PagedResult;
import shop.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);

    Optional<User> getByFullName(String firstName, String lastName);

    List<User> getAll();

    User getById(Long id);

    PagedResult<User> getByLastName(String lastNameFragment, int pageNumber, int pageSize);
}
