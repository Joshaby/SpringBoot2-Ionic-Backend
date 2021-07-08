package com.nelioalves.cursomc.domain;

import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.TipoCliente;

/**
 * Classe que representa a entidade Cliente
 * @author José Henrique
 */
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "TELEFONES")
    private Set<String> telefones =  new HashSet<>();
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidoList = new ArrayList<>();

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getTipo();
    }
    public Cliente() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }
    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }
    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getTipo();
    }
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }
    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }
    public Set<String> getTelefones() {
        return telefones;
    }
    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }
    @JsonIgnore
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }
    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
