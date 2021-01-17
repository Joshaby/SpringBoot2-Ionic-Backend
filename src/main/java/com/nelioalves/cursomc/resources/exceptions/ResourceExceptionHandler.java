package com.nelioalves.cursomc.resources.exceptions;

import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(
        ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest) {

        StandardError standardError = new StandardError(
            HttpStatus.NOT_FOUND.value(),
            objectNotFoundException.getMessage(),
            System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(
        DataIntegrityException dataIntegrityException, HttpServletRequest httpServletRequest) {

        StandardError standardError = new StandardError(
            HttpStatus.BAD_REQUEST.value(),
            dataIntegrityException.getMessage(),
            System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> dataValidation(
        MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest httpServletRequest) {

        ValidationError validationError = new ValidationError(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação",
            System.currentTimeMillis());

        for (FieldError fieldError : methodArgumentNotValidException.getFieldErrors()) {
            validationError.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
