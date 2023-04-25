package ru.nishpal.testing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "Book not found"),
    BOOK_IS_NULL(HttpStatus.BAD_REQUEST, "Book is null");

    private final HttpStatus status;
    private final String exceptionMessage;

    ExceptionMessage(HttpStatus status, String exceptionMessage) {
        this.status = status;
        this.exceptionMessage = exceptionMessage;
    }
}
