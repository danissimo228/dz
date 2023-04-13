package ru.nishpal.crud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.nishpal.crud.entity.User;

import java.util.List;

@Service
public class UserService {
    public User findUserById(int id, List<User> users) {
        return users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User parseJsonToUser(String json) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            return new User(jsonNode.get("username").asText(), jsonNode.get("password").asText());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public HttpStatus setNewUsername(String json, User user) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (user != null) {
                user.setUsername(jsonNode.get("username").asText());
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch (JsonProcessingException e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus updateUser(String json, User user) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            if (user != null) {
                user.setUsername(jsonNode.get("username").asText());
                user.setPassword(jsonNode.get("password").asText());
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch (JsonProcessingException e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
