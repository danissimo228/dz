package ru.nishpal.migrations.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nishpal.migrations.model.exception.ApplicationException;
import ru.nishpal.migrations.model.exception.ExceptionMessage;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("getValidUsers")
    void createUser_WhenLastCreatedUserEqualsAndUserIsValid_ThenReturnsValidUser(UserDto validUserDto) {
        int size = userRepository.findAll().size();

        UserDto createdUserDto = userService.createUser(validUserDto);

        int currentSize = userRepository.findAll().size();

        assertEquals(createdUserDto, validUserDto);
        assertNotEquals(size, currentSize);
    }

    private static Stream<Arguments> getValidUsers() {
        return Stream.of(
                Arguments.of(new UserDto("test1", "test1@gmail.com", "test1234")),
                Arguments.of(new UserDto("test2", "test2@gmail.com", "test")),
                Arguments.of(new UserDto("test-admin", "test-admin@gmail.com", "admin-test"))
        );
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
    void putUser_WhenUserNotEqualsUserAfterUpdate() {
        User user = userRepository.findAll().get(userRepository.findAll().size() - 1);
        long id = user.getId();

        userService.putUser(id, new UserDto("test", "test", "test"));

        User userAfterUpdate = userRepository.findById(id).get();

        assertNotEquals(user, userAfterUpdate);
    }

    @Test
    void putUser_WhenUserWithIdDoesNotExist_ThrowExceptionUserNotFound() {
        int nonExistentId = 36295800;

        ApplicationException exception = Assertions.assertThrows(ApplicationException.class, () -> {
            userService.putUser(nonExistentId, new UserDto("test", "test", "test"));
        });

        assertEquals("User is not found", exception.getExceptionMessage().getMessage());
        assertEquals(ExceptionMessage.USER_NOT_FOUND, exception.getExceptionMessage());
    }
}