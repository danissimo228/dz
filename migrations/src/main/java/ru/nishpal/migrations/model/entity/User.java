package ru.nishpal.migrations.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(
            name = "customIdGenerator",
            strategy = "ru.nishpal.migrations.generator.CustomIdGenerator"
    )
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(String username,
                String email,
                String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
