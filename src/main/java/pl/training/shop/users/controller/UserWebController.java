package pl.training.shop.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.common.PagedResult;
import pl.training.shop.users.model.User;
import pl.training.shop.users.services.UserMapper;
import pl.training.shop.users.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping("show-users.html")
    public ModelAndView showUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        PagedResult<User> users = userService.getAll(pageNumber, pageSize);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("add-user.html")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView("add-user");
        modelAndView.addObject(new User());
        return modelAndView;
    }

    @PostMapping("add-user.html")
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-user";
        } else {
            userService.add(user);
            return "redirect:show-users.html";
        }
    }

}
