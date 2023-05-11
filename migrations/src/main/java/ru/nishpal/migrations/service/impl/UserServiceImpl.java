package ru.nishpal.migrations.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.migrations.model.exception.ApplicationException;
import ru.nishpal.migrations.model.exception.ExceptionMessage;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return UserDto.UserListToDto(userRepository.findAll());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByEmailAndUsername(userDto.getEmail(), userDto.getUsername())) {
            throw new ApplicationException(ExceptionMessage.FIELD_NOT_UNIQUE);
        }
        User user = UserDto.dtoToUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        userRepository.deleteById(id);
        return UserDto.userToDto(user.get());
    }

    @Override
    public UserDto putUser(long id, UserDto userDto) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        }
        if (userRepository.existsByEmailAndUsername(userDto.getEmail(), userDto.getUsername())) {
            throw new ApplicationException(ExceptionMessage.FIELD_NOT_UNIQUE);
        }
        User user = userRepository.findById(id).get();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());

        userRepository.save(user);

        return UserDto.userToDto(user);
    }
}
