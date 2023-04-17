package ru.nishpal.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.nishpal.crud.exception.ApplicationException;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private String exceptionMessage;
    private HttpStatus status;

    public static ExceptionDto exceptionToDto(ApplicationException applicationException) {
        return new ExceptionDto(
                applicationException.getExceptionMessage().getExceptionMessage(),
                applicationException.getExceptionMessage().getStatus()
        );
    }
}
