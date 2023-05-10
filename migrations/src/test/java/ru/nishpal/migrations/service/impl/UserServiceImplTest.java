package ru.nishpal.migrations.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nishpal.migrations.exception.ApplicationException;
import ru.nishpal.migrations.exception.ExceptionMessage;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
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
    void findAllUsers_ReturnAllUsersDto() {
        assertEquals(userService.findAllUsers(), UserDto.UserListToDto(userRepository.findAll()));
    }

    @Test
    void createUser_WhenLastCreatedUserEquals() {
        UserDto userDto = new UserDto("testt", "testt@gmail.com", "tessst");

        int size = userRepository.findAll().size();

        userService.createUser(userDto);

        User lastElement = userRepository.findAll().get(userRepository.findAll().size() - 1);

        int currentSize = userRepository.findAll().size();

        assertEquals(UserDto.userToDto(lastElement), userDto);
        assertNotEquals(size, currentSize);
    }

    @Test
    void deleteUser_WhenLastUserNotEqualsLastUserAfterDelete() {
        User lastUser = userRepository.findAll().get(userRepository.findAll().size() - 1);

        userService.deleteUser(lastUser.getId());

        User lastUserAfterDelete = userRepository.findAll().get(userRepository.findAll().size() - 1);

        assertNotEquals(lastUser, lastUserAfterDelete);
    }

    @Test
    void deleteUser_WhenUserWithIdDoesNotExist_ThrowExceptionUserNotFound() {
        int nonExistentId = 36295800;

        ApplicationException exception = Assertions.assertThrows(ApplicationException.class, () -> {
            userService.deleteUser(nonExistentId);
        });

        assertEquals("User is not found", exception.getExceptionMessage().getMessage());
        assertEquals(ExceptionMessage.USER_NOT_FOUND, exception.getExceptionMessage());
    }

    @Test
    void updateUser_WhenUserNotEqualsUserAfterUpdate() {
        User user = userRepository.findAll().get(userRepository.findAll().size() - 1);
        long id = user.getId();

        userService.updateUser(id, new UserDto("test", "test", "test"));

        User userAfterUpdate = userRepository.findById(id).get();

        assertNotEquals(user, userAfterUpdate);
    }

    @Test
    void updateUser_WhenUserWithIdDoesNotExist_ThrowExceptionUserNotFound() {
        int nonExistentId = 36295800;

        ApplicationException exception = Assertions.assertThrows(ApplicationException.class, () -> {
            userService.updateUser(nonExistentId, new UserDto("test", "test", "test"));
        });

        assertEquals("User is not found", exception.getExceptionMessage().getMessage());
        assertEquals(ExceptionMessage.USER_NOT_FOUND, exception.getExceptionMessage());
    }
}