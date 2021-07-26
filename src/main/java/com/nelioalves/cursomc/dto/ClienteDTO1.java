package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import com.nelioalves.cursomc.domain.Cliente;
import org.hibernate.validator.constraints.Length;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;

/**
 * Classe DTO que contém algumas informações de um Cliente! Esse DTO é usado para atualizar um Cliente no sistema
 * @author José Henrique
 */
@ClienteUpdate
public class ClienteDTO1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O nome é obrigatório!")
    @Length(min = 6, max = 80, message = "O tamanho do nome deve ser entre 6 e 80 caracteres!")
    private String nome;
    @NotEmpty(message = "O email é obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    public ClienteDTO1(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
    public ClienteDTO1() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
