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
        return UserDto.UserListToDto(userService.findAllUsers());
    }

    @PostMapping
    public void postUser(@Valid @RequestBody UserDto userDto) {
        log.info("Get request for post user: {}", userDto);
        userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public void patchUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        log.info("Get request for put user: {}", userDto);
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Get request for delete user: {}", id);
        userService.deleteUser(id);
    }
}
