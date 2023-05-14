package ru.nishpal.migrations.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.nishpal.migrations.model.dto.CreateUserDto;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Testcontainers
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Container
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

    @DynamicPropertySource
    public static void overrideContainerProperties (DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add ("spring.datasource.url", postgreSQLContainer:: getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @ParameterizedTest
    @MethodSource("getValidUser")
    void findAllUsers_WhenUsersExist_ReturnUsersDto(List<User> users) {
        when(userRepository.findAll())
                .thenReturn(users);

        List<UserDto> actualUsers = userService.findAllUsers();

        assertEquals(UserDto.UserListToDto(users), actualUsers);
    }

    private static Stream<Arguments> getValidUser() {
        return Stream.of(
                Arguments.of(new ArrayList<User>()),
                Arguments.of(List.of(
                        new User( "get-test", "get-test@gmial.com", "1234"),
                        new User( "get-test2", "get-test2@gmail.com", "12342"),
                        new User( "test", "test@gmail.com", "test1234")))
        );
    }

    @ParameterizedTest
    @MethodSource("getValidCreateUser")
    void createUser_WhenLastCreatedUserEqualsAndUserIsValid_ThenReturnsValidUser(CreateUserDto createUserDto) {
        CreateUserDto createdUserDto = userService.createUser(createUserDto);

        assertEquals(createdUserDto, createdUserDto);
    }

    private static Stream<Arguments> getValidCreateUser() {
        return Stream.of(
                Arguments.of(new CreateUserDto("post-test", "post-test2@gmail.com", "post1234")),
                Arguments.of(new CreateUserDto( "get-test4", "ge4t-test@gmial.com", "1234")));
    }

    @Test
    void deleteUser_WhenLastUserNotEqualsLastUserAfterDelete() {
    }

    @Test
    void deleteUser_WhenUserWithIdDoesNotExist_ThrowExceptionUserNotFound() {
    }

    @Test
    void putUser_WhenUserNotEqualsUserAfterUpdate() {
    }

    @Test
    void putUser_WhenUserWithIdDoesNotExist_ThrowExceptionUserNotFound() {
    }
}