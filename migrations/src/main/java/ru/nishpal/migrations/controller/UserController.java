package ru.nishpal.migrations.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public UserDto postUser(@Valid @RequestBody UserDto userDto) {
        log.info("Get request for post user: {}", userDto);
        UserDto createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);
        return createdUser;
    }

    @PutMapping("/{id}")
    public UserDto putUser(@PathVariable Long id,
                           @Valid @RequestBody UserDto userDto) {
        log.info("Get request for put user: {}", userDto);
        UserDto updatedUser = userService.putUser(id, userDto);
        log.info("Updated user: {}", updatedUser);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id) {
        log.info("Get request for delete user: {}", id);
        UserDto deletedUser =  userService.deleteUser(id);
        log.info("Deleted user: {}", deletedUser);
        return deletedUser;
    }
}
