package ru.nishpal.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nishpal.migrations.model.entity.User;
import ru.nishpal.migrations.repository.UserRepository;

@SpringBootApplication
public class MigrationsApplication {
    private UserRepository userRepository;
    @Autowired
    public MigrationsApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MigrationsApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        userRepository.save(new User("test", "test", "test"));
//    }
}
