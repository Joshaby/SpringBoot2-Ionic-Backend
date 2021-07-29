package com.nelioalves.cursomc.services.exceptions;

/**
 * Exceção de integridade de dados
 * Essa exceção é jogada quando um método tenta apagar um objeto que possuí referencia a outros objetos, e esses,
 * outros objetos, podem ficar "orfãos"
 * @author José Henrique
 */
public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String message) {
        super(message);
    }
    public DataIntegrityException(String message, Throwable exception) {
        super(message, exception);
    }
}
