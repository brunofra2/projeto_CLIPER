package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class FuncaoDto implements Serializable {
    private final Long id;
    private final String funcao;

    public FuncaoDto(Long id, String funcao) {
        this.id = id;
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncaoDto entity = (FuncaoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.funcao, entity.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, funcao);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "funcao = " + funcao + ")";
    }
}
