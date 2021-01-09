package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer tipo;
    private String descricao;

    private TipoCliente(Integer tipo, String descricao) {
        this.tipo =  tipo;
        this.descricao = descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        for (TipoCliente tipoCliente : TipoCliente.values()) {
            if (tipo.equals(tipoCliente.getTipo()))
                return tipoCliente;
        }
        throw new IllegalArgumentException(String.format("Id %s inválido", tipo));
    }
}
