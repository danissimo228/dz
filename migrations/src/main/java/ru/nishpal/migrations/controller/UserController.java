package ru.nishpal.migrations.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.nishpal.migrations.model.dto.CreateUserDto;
import ru.nishpal.migrations.model.dto.UpdateUserDto;
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
    public CreateUserDto postUser(@Valid @RequestBody CreateUserDto createUserDto) {
        log.info("Get request for post user: {}", createUserDto);

        CreateUserDto createdUser = userService.createUser(createUserDto);

        log.info("Created user: {}", createdUser);

        return createdUser;
    }

    @PutMapping("/{id}")
    public UpdateUserDto putUser(@PathVariable Long id,
                           @Valid @RequestBody UpdateUserDto updateUserDto) {
        log.info("Get request for put user: {}", updateUserDto);

        UpdateUserDto updatedUser = userService.putUser(id, updateUserDto);

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
