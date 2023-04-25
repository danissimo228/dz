package ru.nishpal.testing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nishpal.testing.exception.ExceptionMessage;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String message;
}
