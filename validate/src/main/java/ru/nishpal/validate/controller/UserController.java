package ru.nishpal.validate.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nishpal.validate.model.User;
import ru.nishpal.validate.model.dto.UserDto;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Get request for created user: {}", userDto);

        User user = UserDto.dtoToUser(userDto, 1);

        log.info("Return response for created user: {}", user);

        return userDto;
    }
}
