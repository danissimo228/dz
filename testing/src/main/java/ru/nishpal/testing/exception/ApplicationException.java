package ru.nishpal.testing.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {
    private ExceptionMessage exceptionMessage;
}
