package shop.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.common.PagedResult;
import shop.web.PagedResultTransferObject;
import shop.web.UriBuilder;
import shop.users.model.User;
import shop.users.model.UserTransferObject;
import shop.users.services.UserMapper;
import shop.users.services.UserService;

import java.net.URI;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserTransferObject userTransferObject) {
        var user = userMapper.toUser(userTransferObject);
        var userID = userService.addUser(user).getId();
        URI locationUri = uriBuilder.requestUriWithId(userID);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable Long id) {
        var user = userService.getById(id);
        UserTransferObject userTransferObject = userMapper.toUserTransferObject(user);
        return ResponseEntity.ok(userTransferObject);
    }

    @GetMapping
    public PagedResultTransferObject<UserTransferObject> getUsersByLastName(
            @RequestParam String lastNameFragment,
            @RequestParam(defaultValue = "0")  int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        PagedResult<User> users = userService.getByLastName(lastNameFragment, pageNumber, pageSize);
        return userMapper.toUserTransferObjectsPage(users);

    }
}
