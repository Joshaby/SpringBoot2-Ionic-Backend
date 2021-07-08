package com.nelioalves.cursomc.domain;

import java.util.Date;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

/**
 * Classe que representa uma entidade PagamentoComBoleto
 * @author José Henrique
 */
@Entity
@JsonTypeName("pagementoComBoleto")
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPagamento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataVencimento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataPagamento, Date dataVencimento) {
        super(id, estadoPagamento, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }
    public PagamentoComBoleto() {

    }

    public Date getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public Date getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
