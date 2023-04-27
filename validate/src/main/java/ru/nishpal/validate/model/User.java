package ru.nishpal.validate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
}
