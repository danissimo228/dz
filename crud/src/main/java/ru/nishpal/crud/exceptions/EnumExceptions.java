package ru.nishpal.crud.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public enum EnumExceptions {
    JSON_SERIALIZE((JsonProcessingException) new Exception());

    private final Exception exception;

    EnumExceptions(Exception exception) {
        this.exception = exception;
    }

    public static Exception getException(EnumExceptions exceptions) {
        return exceptions.exception;
    }
}
