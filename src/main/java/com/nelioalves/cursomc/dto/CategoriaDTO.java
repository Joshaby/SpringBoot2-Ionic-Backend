package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Categoria;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
    public CategoriaDTO() {
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
}
