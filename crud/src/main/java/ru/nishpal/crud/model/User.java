package ru.nishpal.crud.model;

import lombok.*;
//import org.hibernate.validator.*;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private static long countId = 1;
    private String name;
    private String lastName;
    private String username;
    private String mail;
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
