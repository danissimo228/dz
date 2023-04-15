package ru.nishpal.crud.exception;

public class ApplicationException extends RuntimeException{

    private ExceptionMessage exceptionMessage;

    public ApplicationException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
