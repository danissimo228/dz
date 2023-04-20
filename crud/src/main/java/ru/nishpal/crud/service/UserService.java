package ru.nishpal.crud.service;

import ru.nishpal.crud.model.User;

import java.util.List;

public interface UserService {
    User findUserById(int id, List<User> users);
    void parseJsonToUser(String json, List<User> users);
    void setNewUsername(String json, User user);
    void updateUser(String json, User user);
}
