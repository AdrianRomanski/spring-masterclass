package shop.users.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.users.model.User;
import shop.users.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Override
    public User addUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public Optional<User> getByFullName(String firstName, String lastName) {
        return usersRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(RuntimeException::new);

    }
}
