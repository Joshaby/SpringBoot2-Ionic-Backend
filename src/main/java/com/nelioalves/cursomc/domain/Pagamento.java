package com.nelioalves.cursomc.domain;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

/**
 * Classe que representa uma entidade abstrata Pagamento
 * @author José Henrique
 */
@Entity
// Define o tipo de herança usada no BD, que nesse caso será herança por subclasse
@Inheritance(strategy = InheritanceType.JOINED)
// Informa que que o JSON que representa um Pagamento terá um campo adicional @type usado para definir o tipo das subclasses
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estadoPagamento;
    @OneToOne
    @JoinColumn(name = "PEDIDO_ID")
    @MapsId
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = (estadoPagamento == null) ? null : estadoPagamento.getTipo();
        this.pedido = pedido;
    }
    public Pagamento() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(estadoPagamento);
    }
    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getTipo();
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
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return getId().equals(pagamento.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
