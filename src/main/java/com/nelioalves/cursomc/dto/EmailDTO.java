package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Classe DTO que representa um Email! Esse DTO é usado na requisição de geração de nova senha
 * @author José Henrique
 */
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    public EmailDTO() {

    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
