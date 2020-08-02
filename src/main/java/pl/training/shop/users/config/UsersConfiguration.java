package pl.training.shop.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.users.services.UserService;
import pl.training.shop.users.repositories.UsersRepository;

@Configuration
public class UsersConfiguration {

    @Bean
    public UserService productService(UsersRepository usersRepository) {
        return new UserService(usersRepository);
    }

}
