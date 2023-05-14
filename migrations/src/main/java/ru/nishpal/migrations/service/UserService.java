package ru.nishpal.migrations.service;

import ru.nishpal.migrations.model.dto.CreateUserDto;
import ru.nishpal.migrations.model.dto.UpdateUserDto;
import ru.nishpal.migrations.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    CreateUserDto createUser(CreateUserDto createUserDto);
    UserDto deleteUser(long id);
    UpdateUserDto putUser(long id, UpdateUserDto updateUserDto);
}
