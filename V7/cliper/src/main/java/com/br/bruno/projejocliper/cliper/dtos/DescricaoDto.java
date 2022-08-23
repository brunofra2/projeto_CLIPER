package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class DescricaoDto implements Serializable {
    private final Long id;
    private final String area;
    private final String descricaoDetalhada;
    private final String escolaridadeDes;
    private final String escolaridadeMin;
    private final String experiencia;
    private final String faixaEtaria;
    private final String habilidade;
    private final String habilitacaoProfissional;
    private final String lidera;
    private final String missao;
    private final String responsabilidades;
    private final String sexo;
    private final String supervisao;
    private final String treinamentoLegais;
    private final String integracao;

    public DescricaoDto(Long id, String area, String descricaoDetalhada, String escolaridadeDes, String escolaridadeMin, String experiencia, String faixaEtaria, String habilidade, String habilitacaoProfissional, String lidera, String missao, String responsabilidades, String sexo, String supervisao, String treinamentoLegais, String integracao) {
        this.id = id;
        this.area = area;
        this.descricaoDetalhada = descricaoDetalhada;
        this.escolaridadeDes = escolaridadeDes;
        this.escolaridadeMin = escolaridadeMin;
        this.experiencia = experiencia;
        this.faixaEtaria = faixaEtaria;
        this.habilidade = habilidade;
        this.habilitacaoProfissional = habilitacaoProfissional;
        this.lidera = lidera;
        this.missao = missao;
        this.responsabilidades = responsabilidades;
        this.sexo = sexo;
        this.supervisao = supervisao;
        this.treinamentoLegais = treinamentoLegais;
        this.integracao = integracao;
    }

    public Long getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public String getEscolaridadeDes() {
        return escolaridadeDes;
    }

    public String getEscolaridadeMin() {
        return escolaridadeMin;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public String getHabilitacaoProfissional() {
        return habilitacaoProfissional;
    }

    public String getLidera() {
        return lidera;
    }

    public String getMissao() {
        return missao;
    }

    public String getResponsabilidades() {
        return responsabilidades;
    }

    public String getSexo() {
        return sexo;
    }

    public String getSupervisao() {
        return supervisao;
    }

    public String getTreinamentoLegais() {
        return treinamentoLegais;
    }

    public String getIntegracao() {
        return integracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescricaoDto entity = (DescricaoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.area, entity.area) &&
                Objects.equals(this.descricaoDetalhada, entity.descricaoDetalhada) &&
                Objects.equals(this.escolaridadeDes, entity.escolaridadeDes) &&
                Objects.equals(this.escolaridadeMin, entity.escolaridadeMin) &&
                Objects.equals(this.experiencia, entity.experiencia) &&
                Objects.equals(this.faixaEtaria, entity.faixaEtaria) &&
                Objects.equals(this.habilidade, entity.habilidade) &&
                Objects.equals(this.habilitacaoProfissional, entity.habilitacaoProfissional) &&
                Objects.equals(this.lidera, entity.lidera) &&
                Objects.equals(this.missao, entity.missao) &&
                Objects.equals(this.responsabilidades, entity.responsabilidades) &&
                Objects.equals(this.sexo, entity.sexo) &&
                Objects.equals(this.supervisao, entity.supervisao) &&
                Objects.equals(this.treinamentoLegais, entity.treinamentoLegais) &&
                Objects.equals(this.integracao, entity.integracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, descricaoDetalhada, escolaridadeDes, escolaridadeMin, experiencia, faixaEtaria, habilidade, habilitacaoProfissional, lidera, missao, responsabilidades, sexo, supervisao, treinamentoLegais, integracao);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "area = " + area + ", " +
                "descricaoDetalhada = " + descricaoDetalhada + ", " +
                "escolaridadeDes = " + escolaridadeDes + ", " +
                "escolaridadeMin = " + escolaridadeMin + ", " +
                "experiencia = " + experiencia + ", " +
                "faixaEtaria = " + faixaEtaria + ", " +
                "habilidade = " + habilidade + ", " +
                "habilitacaoProfissional = " + habilitacaoProfissional + ", " +
                "lidera = " + lidera + ", " +
                "missao = " + missao + ", " +
                "responsabilidades = " + responsabilidades + ", " +
                "sexo = " + sexo + ", " +
                "supervisao = " + supervisao + ", " +
                "treinamentoLegais = " + treinamentoLegais + ", " +
                "integracao = " + integracao + ")";
    }
}
