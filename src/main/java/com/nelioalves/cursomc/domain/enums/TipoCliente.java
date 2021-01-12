package com.nelioalves.cursomc.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer tipo;
    private String descricao;

    TipoCliente(Integer tipo, String descricao) {
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
        Optional<TipoCliente> optionalTipoCliente = Arrays.stream(
                TipoCliente.values()).filter(TipoCliente -> TipoCliente.getTipo().equals(tipo)).findFirst();
        return optionalTipoCliente.orElseThrow(
                () -> new IllegalArgumentException(String.format("Id %s inválido", tipo)));
    }
}
