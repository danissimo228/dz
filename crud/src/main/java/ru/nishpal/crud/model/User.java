package ru.nishpal.crud.model;

import lombok.*;
import ru.nishpal.crud.annotation.Email;
import ru.nishpal.crud.annotation.NotNull;
import ru.nishpal.crud.annotation.Size;
@Data
@NoArgsConstructor
public class User {
    @NotNull
    private long id;
    private static long countId = 1;
    @NotNull
    @Size(min = 2, max = 25)
    private String name;
    @NotNull
    @Size(min = 2, max = 25)
    private String lastName;
    @NotNull
    @Size(min = 4, max = 25)
    private String username;
    @NotNull
    @Email
    private String mail;
    @NotNull
    @Size(min = 4, max = 25)
    private String password;

    public User(String name, String lastName, String username, String mail, String password) {
        this.id = countId++;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.mail = mail;
        this.password = password;
    }
}
