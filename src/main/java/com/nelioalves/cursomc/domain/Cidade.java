package com.nelioalves.cursomc.domain;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa uma entidade Cidade
 * @author José Henrique
 */
@Entity
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "ESTADO_ID")
    private Estado estado;

    public Cidade(Integer id, String name, Estado estado) {
        this.id = id;
        this.name = name;
        this.estado = estado;
    }
    public Cidade() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade)) return false;
        Cidade cidade = (Cidade) o;
        return getId().equals(cidade.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
