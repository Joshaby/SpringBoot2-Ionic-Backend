package com.nelioalves.cursomc.services.validation;

import javax.validation.Payload;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Anotação para validar inserção de Clientes
 */
@Constraint(validatedBy = ClienteInsertValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteValidator {

    String message() default "Erro de validação";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}