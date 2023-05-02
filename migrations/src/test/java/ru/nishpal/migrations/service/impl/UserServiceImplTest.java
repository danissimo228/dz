package ru.nishpal.migrations.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserServiceImplTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @Test
    void findAllUsers_ReturnAllUsers() {
        assertEquals(userService.findAllUsers(), userRepository.findAll());
    }

    @Test
    void createUser_WhenLastCreatedUserEquals() {
        UserDto userDto = new UserDto("testt", "testt", "tessst");
        userService.createUser(userDto);
        assertEquals(UserDto.userToDto(userRepository.findAll().get(userRepository.findAll().size() - 1)), userDto);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}