package ru.nishpal.crud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nishpal.crud.exception.ApplicationException;
import ru.nishpal.crud.model.dto.ExceptionDto;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handler(ApplicationException applicationException) {
        log.error("catch exception: ", applicationException);
        ExceptionDto exceptionDto = ExceptionDto
                .builder()
                .exceptionMessage(applicationException.getExceptionMessage())
                .message(applicationException.getExceptionMessage().getExceptionMessage())
                .build();
        log.error("return response : ExceptionDto={}", exceptionDto);
        return ResponseEntity.status(applicationException.getExceptionMessage().getStatus()).body(exceptionDto);
    }
}
