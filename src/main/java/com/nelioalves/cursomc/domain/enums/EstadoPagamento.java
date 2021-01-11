package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {

    PEDENTE(1, "Pendente"),
    CANCELADO(2, "Cancelado"),
    QUITADO(3, "Quitado");

    private Integer tipo;
    private String descricao;

    EstadoPagamento(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if (estadoPagamento.getTipo().equals(tipo))
                return estadoPagamento;
        }
        throw new IllegalArgumentException(String.format("Id %s inválido", tipo));
    }
}
