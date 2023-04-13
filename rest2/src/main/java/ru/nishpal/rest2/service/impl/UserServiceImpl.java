package ru.nishpal.rest2.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.rest2.entity.User;
import ru.nishpal.rest2.service.UserService;

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
