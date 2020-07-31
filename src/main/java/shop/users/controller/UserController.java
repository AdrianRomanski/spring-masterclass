package shop.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.common.PagedResult;
import shop.users.model.User;
import shop.users.model.UserTransferObject;
import shop.users.services.UserMapper;
import shop.users.services.UserService;
import shop.web.PagedResultTransferObject;
import shop.web.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserTransferObject userTransferObject,
                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        var user = userMapper.toUser(userTransferObject);
        var userID = userService.addUser(user).getId();
        URI locationUri = uriBuilder.requestUriWithId(userID);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable Long id) {
        var user = userService.getById(id);
        UserTransferObject userTransferObject = userMapper.toUserTransferObject(user);
        userTransferObject.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
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

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ExceptionTransferObject> onUserNotFound(UserNotFoundException exception) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ExceptionTransferObject("User not found"));
//    }
}
