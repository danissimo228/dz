package ru.nishpal.migrations.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
    FIELD_NOT_VALID(HttpStatus.BAD_REQUEST, "Field in user not valid"),
    FIELD_NOT_UNIQUE(HttpStatus.BAD_REQUEST, "This username or email is already taken");

    private final HttpStatus status;
    private final String message;

    ExceptionMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
