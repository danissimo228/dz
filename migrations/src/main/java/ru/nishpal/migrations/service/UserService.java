package ru.nishpal.migrations.service;

import ru.nishpal.migrations.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto createUser(UserDto userDto);
    void deleteUser(long id);
    void updateUser(long id, UserDto userDto);
}
