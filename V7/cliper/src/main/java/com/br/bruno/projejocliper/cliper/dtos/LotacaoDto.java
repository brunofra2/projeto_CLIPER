package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class LotacaoDto implements Serializable {
    private final Integer idLocacao;
    private final String titulo;

    public LotacaoDto(Integer idLocacao, String titulo) {
        this.idLocacao = idLocacao;
        this.titulo = titulo;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotacaoDto entity = (LotacaoDto) o;
        return Objects.equals(this.idLocacao, entity.idLocacao) &&
                Objects.equals(this.titulo, entity.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLocacao, titulo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idLocacao = " + idLocacao + ", " +
                "titulo = " + titulo + ")";
    }
}
