package ru.nishpal.crud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.nishpal.crud.model.User;
import ru.nishpal.crud.exception.ApplicationException;
import ru.nishpal.crud.exception.ExceptionMessage;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public User findUserById(int id, List<User> users) {
        return users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User parseJsonToUser(String json) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            System.out.println(jsonNode.get("username").asText());
            return new User(jsonNode.get("username").asText(), jsonNode.get("password").asText());
        } catch (JsonProcessingException e) {
            throw new ApplicationException(ExceptionMessage.BAD_JSON_SERIALIZATION);
        }
    }

    public void setNewUsername(String json, User user) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (user != null) {
                user.setUsername(jsonNode.get("username").asText());
            } else {
                throw new ApplicationException(ExceptionMessage.USER_IS_NULL);
            }
        } catch (JsonProcessingException e) {
            throw new ApplicationException(ExceptionMessage.BAD_JSON_SERIALIZATION);
        }
    }

    public void updateUser(String json, User user) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (user != null) {
                user.setUsername(jsonNode.get("username").asText());
                user.setPassword(jsonNode.get("password").asText());
            }
            throw new ApplicationException(ExceptionMessage.USER_IS_NULL);
        } catch (JsonProcessingException | NullPointerException e) {
            throw new ApplicationException(ExceptionMessage.BAD_JSON_SERIALIZATION);
        }
    }
}
