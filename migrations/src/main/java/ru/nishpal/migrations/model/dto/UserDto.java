package ru.nishpal.migrations.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nishpal.migrations.model.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDto {
    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;

    @Email
    private String email;

    @Size(min = 4, max = 20)
    private String password;

    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User dtoToUser(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }

    public static List<UserDto> UserListToDto(List<User> users) {
        return users.stream()
                .map(UserDto::userToDto)
                .collect(Collectors.toList());
    }
}