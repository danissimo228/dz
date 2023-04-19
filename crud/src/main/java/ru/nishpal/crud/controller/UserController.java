package ru.nishpal.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.crud.model.User;
import ru.nishpal.crud.model.dto.UserDto;
import ru.nishpal.crud.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    //  1. object CreateUserDto
    //  2. fields: String(not null), int(!= 0), Date(> currentDate)
    //  3. annotation hibernate.validation
    //  4. Обработчик на exception`ы в ControllerAdvice
    //  .5 Добавить логи
    //  6. Добавить филды в User +
    @PostMapping
    public HttpStatus createUser(@RequestBody String json) {
        User user = userService.parseJsonToUser(json);
        this.users.add(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        this.users.remove(user);
        return HttpStatus.OK;
    }

    @PatchMapping("/{id}")
    public HttpStatus updateUsersUsernameById(@RequestBody String jsonUsername, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        userService.setNewUsername(jsonUsername, user);
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public HttpStatus updateUserById(@RequestBody String jsonUsernameAndPassword, @PathVariable("id") int id) {
        User user = userService.findUserById(id, users);
        userService.updateUser(jsonUsernameAndPassword, user);
        return HttpStatus.OK;
    }
}
