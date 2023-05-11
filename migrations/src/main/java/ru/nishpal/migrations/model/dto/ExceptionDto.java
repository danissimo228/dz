package ru.nishpal.migrations.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.nishpal.migrations.model.exception.ExceptionMessage;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String message;
}
