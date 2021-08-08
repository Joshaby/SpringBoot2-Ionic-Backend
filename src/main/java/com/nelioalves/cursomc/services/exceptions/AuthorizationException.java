package com.nelioalves.cursomc.services.exceptions;

/**
 * Exceção de autorização
 * Essa exceção é jogada quando um usuário tenta acessar alguma informação que não pertence a ele
 * @author José Henrique
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String message) {
        super(message);
    }
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
