package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class PgrDto implements Serializable {
    private final Long id;
    private final String ano;

    public PgrDto(Long id, String ano) {
        this.id = id;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public String getAno() {
        return ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PgrDto entity = (PgrDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ano, entity.ano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ano);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "ano = " + ano + ")";
    }
}
