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
    public ResponseEntity<String> handler(ApplicationException applicationException) {
        ExceptionDto exceptionDto = ExceptionDto.exceptionToDto(applicationException);
        log.error(exceptionDto.getExceptionMessage(), exceptionDto);
        return new ResponseEntity<>(exceptionDto.getExceptionMessage(), exceptionDto.getStatus());
    }
}
