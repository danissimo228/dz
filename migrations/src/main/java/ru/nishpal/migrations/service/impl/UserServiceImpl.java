package ru.nishpal.migrations.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.nishpal.migrations.exception.ApplicationException;
import ru.nishpal.migrations.exception.ExceptionMessage;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.size() == 0)
            throw new ApplicationException(ExceptionMessage.USERS_NOT_FOUND);
        return UserDto.UserListToDto(users);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserDto.dtoToUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        if (userRepository.findById(id).isEmpty())
            throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        userRepository.delete(userRepository.findById(id).get());
    }

    @Override
    public void updateUser(long id, UserDto userDto) {
        if (userRepository.findById(id).isEmpty())
            throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        User user = userRepository.findById(id).get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }
}
