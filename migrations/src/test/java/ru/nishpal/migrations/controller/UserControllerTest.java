package ru.nishpal.migrations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.nishpal.migrations.exception.ApplicationException;
import ru.nishpal.migrations.exception.ExceptionMessage;
import ru.nishpal.migrations.model.dto.UserDto;
import ru.nishpal.migrations.repository.UserRepository;
import ru.nishpal.migrations.service.UserService;

import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    UserController userController;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    private static final String URL = "/api/v1/user";

    @Test
    void getAllUsers_ThenStatusIsOkAndReturnsAllUsersDto() throws Exception {
        userService.createUser(new UserDto("test", "test", "test"));
        List<UserDto> usersDto = userService.findAllUsers();
        mockMvc.perform(
                        get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(usersDto)));
    }

    @Test
    void getAllUsers_WhenUsersIsEmpty_ThenStatusNotFoundAndReturnsApplicationException() {

    }

    @Test
    void postUser_ThenStatusIsOkAndReturnsCreatedUser() throws Exception {
        UserDto userDto = new UserDto("test-post", "test@gmail.com", "test-post");

        mockMvc.perform(
                        post(URL)
                                .content(objectMapper.writeValueAsString(userDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    void postUser_WhenUsersFieldNotValid_ThenStatus400AndReturnsApplicationException() throws Exception {
        UserDto userDto = new UserDto("t", "test@gmail.com", "test-post");

        mockMvc.perform(
                        post(URL)
                                .content(objectMapper.writeValueAsString(userDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult ->
                        Objects.equals(mvcResult.getResolvedException(), new ApplicationException(ExceptionMessage.FIELD_NOT_VALID)));
    }

    @Test
    void putUser() throws Exception {
        UserDto userDto = new UserDto("test-put", "testput@gmail.com", "test-put");

        long id = userRepository.findAll().get(userRepository.findAll().size() - 1).getId();

        mockMvc.perform(
                        put(URL + "/" + id)
                                .content(objectMapper.writeValueAsString(userDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(mvcResult -> userRepository.findById(id).get().equals(userDto));
    }

    @Test
    void deleteUser() {
    }
}