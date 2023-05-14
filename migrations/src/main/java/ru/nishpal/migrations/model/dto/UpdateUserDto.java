package ru.nishpal.migrations.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nishpal.migrations.model.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;

    public static User updateUserDtoToUser(UpdateUserDto updateUserDto, User user) {
        user.setUsername(updateUserDto.getUsername());
        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getPassword());
        return user;
    }
}
