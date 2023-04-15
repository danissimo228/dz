package ru.nishpal.crud.service;

import org.springframework.http.HttpStatus;
import ru.nishpal.crud.model.User;

import java.util.List;

public interface UserService {
    User findUserById(int id, List<User> users);
    User parseJsonToUser(String json);
    void setNewUsername(String json, User user);
    void updateUser(String json, User user);
}
