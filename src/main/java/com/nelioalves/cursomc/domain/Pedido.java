package com.nelioalves.cursomc.domain;

import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe que representa uma entidade Pedido
 * @author José Henrique
 */
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "ENDERECO")
    private Endereco endereco;
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itemPedidoSet = new HashSet<>();

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.endereco = endereco;
    }
    public Pedido() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getInstante() {
        return instante;
    }
    public void setInstante(Date instante) {
        this.instante = instante;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Set<ItemPedido> getItemPedidoSet() {
        return itemPedidoSet;
    }
    public void setItemPedidoSet(Set<ItemPedido> itemPedidoSet) {
        this.itemPedidoSet = itemPedidoSet;
    }
    public Double getTotalPedido() {
        Double result = 0.0;
        for (ItemPedido itemPedido : getItemPedidoSet()) {
            result += itemPedido.getSubtotal();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getId().equals(pedido.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Número: %d\n", getId()));
        stringBuilder.append(String.format("Instante: %s\n", simpleDateFormat.format(getInstante())));
        stringBuilder.append(String.format("Cliente: %s\n", getCliente().getNome()));
        stringBuilder.append(String.format("Situação: %s\n", getPagamento().getEstadoPagamento().getDescricao()));
        stringBuilder.append("Detalhes: \n");
        for (ItemPedido itemPedido : itemPedidoSet) {
            stringBuilder.append(itemPedido.toString() + "\n");
        }
        stringBuilder.append(String.format("Valor total: %s\n", numberFormat.format(getTotalPedido())));
        return stringBuilder.toString();
    }
}
