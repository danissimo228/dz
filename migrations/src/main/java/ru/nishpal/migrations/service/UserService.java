package ru.nishpal.migrations.service;

import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    void createUser(UserDto userDto);
    void deleteUser(long id);
    void updateUser(long id, UserDto userDto);
}
