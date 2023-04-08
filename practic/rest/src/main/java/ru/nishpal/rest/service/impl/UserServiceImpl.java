package ru.nishpal.rest.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.rest.entity.User;
import ru.nishpal.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String showUser(User user) {
        return user.toString();
    }

    @Override
    public String userSay() {
        return "hello!";
    }
}
