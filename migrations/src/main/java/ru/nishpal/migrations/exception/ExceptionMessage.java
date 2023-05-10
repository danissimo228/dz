package ru.nishpal.migrations.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
    USERS_NOT_FOUND(HttpStatus.NOT_FOUND, "Users is not found"),
    FIELD_NOT_VALID(HttpStatus.BAD_REQUEST, "Field in user not valid");

    private final HttpStatus status;
    private final String message;

    ExceptionMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
