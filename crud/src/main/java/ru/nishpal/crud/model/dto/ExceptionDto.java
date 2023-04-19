package ru.nishpal.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nishpal.crud.exception.ExceptionMessage;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String message;
}
