package com.nelioalves.cursomc.services.exceptions;

/**
 * Exceção para objetos não encontrados no banco
 * Como o próprio nome da exceção diz, se trata de uma exceção que é jogada quando algum objeto não encotrado
 * @author José Henrique
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
