package com.nelioalves.cursomc.domain;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

/**
 * Classe que representa uma entidade PagamentoComCartao
 * @author José Henrique
 */
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
    public PagamentoComCartao() {

    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }
    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
