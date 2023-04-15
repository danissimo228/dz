package ru.nishpal.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.crud.model.User;
import ru.nishpal.crud.model.dto.UserDto;
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
    public List<UserDto> showAllUsers() {
        return UserDto.listOfUsersToDto(users);
    }

    @GetMapping("/show/{id}")
    public UserDto showUserById(@PathVariable("id") int id)  {
        return UserDto.userToDto(userService.findUserById(id, users));
    }

    @PostMapping("/create")
    public HttpStatus createUser(@RequestBody String json) {
        User user = userService.parseJsonToUser(json);
        if (user == null)
            return HttpStatus.BAD_REQUEST;
        this.users.add(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        if (user == null)
            return HttpStatus.BAD_REQUEST;
        this.users.remove(user);
        return HttpStatus.OK;
    }

    @PatchMapping("/update-username/{id}")
    public HttpStatus updateUsersUsernameById(@RequestBody String jsonUsername, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        if (user == null)
            return HttpStatus.BAD_REQUEST;
        userService.setNewUsername(jsonUsername, user);
        return HttpStatus.OK;
    }

    @PutMapping("/update/{id}")
    public HttpStatus updateUserById(@RequestBody String jsonUsernameAndPassword, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        if (user == null)
            return HttpStatus.BAD_REQUEST;
        userService.updateUser(jsonUsernameAndPassword, user);
        return HttpStatus.OK;
    }
}
