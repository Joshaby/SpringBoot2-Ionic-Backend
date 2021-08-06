package com.nelioalves.cursomc.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer tipo;
    private String descricao;

    Perfil(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer tipo) {
        if (tipo == null) {
            return null;
        }
        Optional<Perfil> perfil = Arrays.stream(Perfil.values()).filter(p -> p.getTipo().equals(tipo)).findFirst();
        return perfil.orElseThrow(() -> new IllegalArgumentException(String.format("Id %d inválido", tipo)));
    }
}
