package ru.nishpal.migrations.service.impl;

import org.springframework.stereotype.Service;
import ru.nishpal.migrations.model.dto.CreateUserDto;
import ru.nishpal.migrations.model.dto.UpdateUserDto;
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
    public CreateUserDto createUser(CreateUserDto createUserDto) {
        if (userRepository.existsByEmailAndUsername(createUserDto.getEmail(), createUserDto.getUsername())) {
            throw new ApplicationException(ExceptionMessage.FIELD_NOT_UNIQUE);
        }
        User user = CreateUserDto.createUserDtoToUser(createUserDto);
        userRepository.save(user);
        return createUserDto;
    }

    @Override
    public UserDto deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        userRepository.deleteById(id);
        return UserDto.userToDto(user.get());
    }

    @Override
    public UpdateUserDto putUser(long id, UpdateUserDto updateUserDto) {
        if (!userRepository.existsById(id)) {
            throw new ApplicationException(ExceptionMessage.USER_NOT_FOUND);
        }
        if (userRepository.existsByEmailAndUsername(updateUserDto.getEmail(), updateUserDto.getUsername())) {
            throw new ApplicationException(ExceptionMessage.FIELD_NOT_UNIQUE);
        }
        User user = userRepository.findById(id).get();
        UpdateUserDto.updateUserDtoToUser(updateUserDto, user);

        userRepository.save(user);

        return updateUserDto;
    }
}
