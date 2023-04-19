package ru.nishpal.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nishpal.crud.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDto {
    private long id;

    private String name;
    private String lastName;
    private String username;
    private String mail;

    public static UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getUsername(),
                user.getMail()
        );
    }

    public static List<UserDto> listOfUsersToDto(List<User> users) {
        return users.stream().map(UserDto::userToDto).collect(Collectors.toList());
    }
}
