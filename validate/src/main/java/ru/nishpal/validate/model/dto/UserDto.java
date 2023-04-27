package ru.nishpal.validate.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import ru.nishpal.validate.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDto {
    @ToString.Exclude
    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;
    @Email
    @ToString.Exclude
    private String email;
    @NotEmpty
    @Size(min = 4, max = 20)
    @ToString.Exclude
    private String password;
    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User dtoToUser(UserDto userDto, long id) {
        return new User(
                id,
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }

    public static List<UserDto> userListToDto(List<User> users) {
        return users.stream()
                .map(UserDto::userToDto)
                .collect(Collectors.toList());
    }
}
