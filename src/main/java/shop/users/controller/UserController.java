package shop.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.common.UriBuilder;
import shop.users.model.User;
import shop.users.services.UserService;

import java.net.URI;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        var userID = userService.addUser(user).getId();
        URI locationUri = uriBuilder.requestUriWithId(userID);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        var user = userService.getById(id);
        return ResponseEntity.of(user);
    }
}
