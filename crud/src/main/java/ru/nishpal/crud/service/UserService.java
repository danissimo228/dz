package ru.nishpal.crud.service;

import org.springframework.http.HttpStatus;
import ru.nishpal.crud.entity.User;

import java.util.List;

public interface UserService {
    User findUserById(int id, List<User> users);
    User parseJsonToUser(String json) throws Exception;
    HttpStatus setNewUsername(String json, User user);
    HttpStatus updateUser(String json, User user);
}
