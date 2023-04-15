package ru.nishpal.crud.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    BAD_JSON_SERIALIZATION(HttpStatus.BAD_REQUEST, "Json not serializable"),
    USER_IS_NULL(HttpStatus.BAD_REQUEST, "User is null");

    private final HttpStatus status;
    private final String exceptionMessage;

    ExceptionMessage(HttpStatus status, String exceptionMessage) {
        this.status = status;
        this.exceptionMessage = exceptionMessage;
    }
}
