package com.nelioalves.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    @JsonIgnore
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoPK)) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return getProduto().equals(that.getProduto()) && getPedido().equals(that.getPedido());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getProduto(), getPedido());
    }
}
