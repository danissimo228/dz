package ru.nishpal.rest.service;

import ru.nishpal.rest.entity.User;

public interface UserService {
    String showUser(User user);

    String userSay();
}
