package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class PeriodoDto implements Serializable {
    private final Long id;
    private final String periodo;

    public PeriodoDto(Long id, String periodo) {
        this.id = id;
        this.periodo = periodo;
    }

    public Long getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoDto entity = (PeriodoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.periodo, entity.periodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "periodo = " + periodo + ")";
    }
}
