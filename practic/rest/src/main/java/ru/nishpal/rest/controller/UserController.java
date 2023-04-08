package ru.nishpal.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nishpal.rest.entity.User;
import ru.nishpal.rest.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private static User user = new User(1, "Danya", "qwwerty");

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/show")
    public String showUser() {
        return userService.showUser(user);
    }

    @GetMapping("/say")
    public String userSay() {
        return userService.userSay();
    }
}
