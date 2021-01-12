package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_ID")
    )
    private List<Categoria> categoriasList = new ArrayList<>();
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itemPedidoSet = new HashSet<>();

    public Produto() {

    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public List<Categoria> getCategoriaList() {
        return categoriasList;
    }
    @JsonIgnore
    public void setCategoriaList(List<Categoria> categoriaLst) {
        this.categoriasList = categoriaLst;
    }
    @JsonIgnore
    public Set<ItemPedido> getItemPedidoSet() {
        return itemPedidoSet;
    }
    public void setItemPedidoSet(Set<ItemPedido> itemPedidoSet) {
        this.itemPedidoSet = itemPedidoSet;
    }
    @JsonIgnore
    public List<Pedido> getPedidos() {
        return itemPedidoSet.stream().map(ItemPedido::getPedido).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
