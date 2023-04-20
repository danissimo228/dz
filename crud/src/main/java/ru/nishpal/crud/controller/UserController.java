package ru.nishpal.crud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.crud.model.User;
import ru.nishpal.crud.model.dto.UserDto;
import ru.nishpal.crud.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private List<User> users = Stream.of(
            new User("Dima", "Bobrov", "bobr777", "bobrov@gmail.com", "qwerty"),
            new User("Dima", "Ivanov", "dimooon", "dmitriy@gmail.com", "12345"),
            new User("Daniil", "Pirojkov", "admin", "daniil121@gmail.com", "root"),
            new User("Masha", "Ivanova", "marusya888", "Masha555@gmail.com", "mashshaa")
    ).collect(Collectors.toList());

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> showAllUsers() {
        return UserDto.listOfUsersToDto(users);
    }

    @GetMapping("/{id}")
    public UserDto showUserById(@PathVariable("id") int id)  {
        return UserDto.userToDto(userService.findUserById(id, users));
    }

    // TODO
    //  1. create object CreateUserDto fields: String(not null), int(!= 0), Date(> currentDate)
    @PostMapping
    public HttpStatus createUser(@RequestBody String json) {
        userService.parseJsonToUser(json, this.users);
        log.info("Create user: {}", users.get(users.size()-1));
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        this.users.remove(user);
        log.info("Delete user: {}", user);
        return HttpStatus.OK;
    }

    @PatchMapping("/{id}")
    public HttpStatus updateUsersUsernameById(@RequestBody String jsonUsername, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        userService.setNewUsername(jsonUsername, user);
        log.info("Updated username for user: {}", user);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus updateUserById(@RequestBody String jsonUsernameAndPassword, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        userService.updateUser(jsonUsernameAndPassword, user);
        log.info("Update user by id: {}", user);
        return HttpStatus.OK;
    }
}
