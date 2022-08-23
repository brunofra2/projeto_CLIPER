package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class RiscoDto implements Serializable {
    private final Integer idRisco;
    private final String fatorDeRisco;
    private final String classificacao;
    private final String categoria;
    private final String intencidade;
    private final FuncaoDto idCargo;

    public RiscoDto(Integer idRisco, String fatorDeRisco, String classificacao, String categoria, String intencidade, FuncaoDto idCargo) {
        this.idRisco = idRisco;
        this.fatorDeRisco = fatorDeRisco;
        this.classificacao = classificacao;
        this.categoria = categoria;
        this.intencidade = intencidade;
        this.idCargo = idCargo;
    }

    public Integer getIdRisco() {
        return idRisco;
    }

    public String getFatorDeRisco() {
        return fatorDeRisco;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIntencidade() {
        return intencidade;
    }

    public FuncaoDto getIdCargo() {
        return idCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiscoDto entity = (RiscoDto) o;
        return Objects.equals(this.idRisco, entity.idRisco) &&
                Objects.equals(this.fatorDeRisco, entity.fatorDeRisco) &&
                Objects.equals(this.classificacao, entity.classificacao) &&
                Objects.equals(this.categoria, entity.categoria) &&
                Objects.equals(this.intencidade, entity.intencidade) &&
                Objects.equals(this.idCargo, entity.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRisco, fatorDeRisco, classificacao, categoria, intencidade, idCargo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idRisco = " + idRisco + ", " +
                "fatorDeRisco = " + fatorDeRisco + ", " +
                "classificacao = " + classificacao + ", " +
                "categoria = " + categoria + ", " +
                "intencidade = " + intencidade + ", " +
                "idCargo = " + idCargo + ")";
    }
}
