package ru.nishpal.migrations.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = UserDto.dtoToUser(userDto);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        if (userRepository.findById(id).isPresent())
            userRepository.delete(userRepository.findById(id).get());
    }
}
