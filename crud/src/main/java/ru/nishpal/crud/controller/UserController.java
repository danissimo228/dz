package ru.nishpal.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.crud.entity.User;
import ru.nishpal.crud.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private List<User> users = Stream.of(
            new User("test", "qwerty"),
            new User("Dima", "1234"),
            new User("admin", "root"),
            new User("Masha", "22848")
    ).collect(Collectors.toList());

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public List<User> showAllUsers() {
        return users;
    }

    @GetMapping("/show/{id}")
    public User showUserById(@PathVariable("id") int id)  {
        return userService.findUserById(id, users);
    }

    @PostMapping("/create")
    public HttpStatus createUser(@RequestBody String json) throws Exception {
        User user = userService.parseJsonToUser(json);
        if (user == null)
            return HttpStatus.BAD_REQUEST;
        this.users.add(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        this.users.remove(user);
    }

    @PatchMapping("/update-username/{id}")
    public HttpStatus updateUsersUsernameById(@RequestBody String jsonUsername, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        return userService.setNewUsername(jsonUsername, user);
    }

    @PutMapping("/update/{id}")
    public HttpStatus updateUserById(@RequestBody String jsonUsernameAndPassword, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        return userService.updateUser(jsonUsernameAndPassword, user);
    }
}
