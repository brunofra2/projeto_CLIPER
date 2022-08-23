package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CursoDto implements Serializable {
    private final Long id;
    private final Date dataInicio;
    private final Date dataTermino;
    private final String escola;
    private final String grauInstrucao;
    private final String nome;

    public CursoDto(Long id, Date dataInicio, Date dataTermino, String escola, String grauInstrucao, String nome) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.escola = escola;
        this.grauInstrucao = grauInstrucao;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public String getEscola() {
        return escola;
    }

    public String getGrauInstrucao() {
        return grauInstrucao;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoDto entity = (CursoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dataInicio, entity.dataInicio) &&
                Objects.equals(this.dataTermino, entity.dataTermino) &&
                Objects.equals(this.escola, entity.escola) &&
                Objects.equals(this.grauInstrucao, entity.grauInstrucao) &&
                Objects.equals(this.nome, entity.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataInicio, dataTermino, escola, grauInstrucao, nome);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dataInicio = " + dataInicio + ", " +
                "dataTermino = " + dataTermino + ", " +
                "escola = " + escola + ", " +
                "grauInstrucao = " + grauInstrucao + ", " +
                "nome = " + nome + ")";
    }
}
