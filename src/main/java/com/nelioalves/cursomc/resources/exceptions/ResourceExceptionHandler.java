package com.nelioalves.cursomc.resources.exceptions;

import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

/**
 * Classe que representa um manipulador de exceções
 * @author José Henrique
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Método que trata uma ObjectNotFoundException
     * @param objectNotFoundException Exceção recebida
     * @param httpServletRequest Requisição HTTO do usuário
     * @return um JSON de resposta com a (ResponseEntity) da exceção e erro 404(Not Found)
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(
        ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest) {

        StandardError standardError = new StandardError(
            HttpStatus.NOT_FOUND.value(),
            objectNotFoundException.getMessage(),
            System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    /**
     * Método que trata uma DataIntegrityException
     * @param dataIntegrityException exceção recebida
     * @param httpServletRequest Requisição HTTO do usuário
     * @return Um JSON de resposta com a mensagem(ResponseEntity) da exceção e um erro HTTP 400(Bad Request)
     */
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(
        DataIntegrityException dataIntegrityException, HttpServletRequest httpServletRequest) {

        StandardError standardError = new StandardError(
            HttpStatus.BAD_REQUEST.value(),
            dataIntegrityException.getMessage(),
            System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    /**
     * Método que trata uma MethodArgumentNotValidException
     * @param methodArgumentNotValidException Exceção recebida
     * @param httpServletRequest Requisição HTTO do usuário
     * @return Um JSON como resposta(RespondeEntity) com a mensagem da exceção e erro HTTP 400(Bad Request)
     */
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

    /**
     * Método que trata uma AuthorizationException
     * @param authorizationException A exceção recebida
     * @param httpServletRequest Requisição HTTO do usuário
     * @return Um JSON de respostas(ResponseEntity) com a mensagem da exceção no corpo e erro HTTP forbidden(403)
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authotizarion(AuthorizationException authorizationException,
            HttpServletRequest httpServletRequest)  {

        StandardError standardError = new StandardError(
                HttpStatus.FORBIDDEN.value(),
                authorizationException.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }
}
