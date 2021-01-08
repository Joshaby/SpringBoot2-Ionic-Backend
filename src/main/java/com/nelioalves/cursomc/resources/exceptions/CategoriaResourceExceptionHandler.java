package com.nelioalves.cursomc.resources.exceptions;

import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CategoriaResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpRequest) {
        StandartError standartError = new StandartError(
                HttpStatus.NOT_FOUND.value(),
                objectNotFoundException.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }
}
