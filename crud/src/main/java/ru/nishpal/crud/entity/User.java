package ru.nishpal.crud.entity;

import lombok.*;

@Data
@NoArgsConstructor
public class User {
    private int id;

    private static int countId = 1;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = countId++;
        this.username = username;
        this.password = password;
    }
}
