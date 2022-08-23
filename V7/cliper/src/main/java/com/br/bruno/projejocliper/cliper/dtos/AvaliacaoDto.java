package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AvaliacaoDto implements Serializable {
    private final Long idAvaliacao;
    private final Date dataExames;
    private final Date dataTreinamento;
    private final Date dataAvaliacao;
    private final Date fimExperiencia;
    private final String estatusAvaliacao;
    private final String tempoAtual;
    private final Double salarioAtual;
    private final Double salarioProposto;
    private final String exames;
    private final String condicao;
    private final String treinamentos;
    private final Date dataSup;
    private final String situacao;
    private final String parecerSup;
    private final Date dataGen;
    private final String parecerGen;
    private final Date dataRh;
    private final String parecerRh;

    public AvaliacaoDto(Long idAvaliacao, Date dataExames, Date dataTreinamento, Date dataAvaliacao, Date fimExperiencia, String estatusAvaliacao, String tempoAtual, Double salarioAtual, Double salarioProposto, String exames, String condicao, String treinamentos, Date dataSup, String situacao, String parecerSup, Date dataGen, String parecerGen, Date dataRh, String parecerRh) {
        this.idAvaliacao = idAvaliacao;
        this.dataExames = dataExames;
        this.dataTreinamento = dataTreinamento;
        this.dataAvaliacao = dataAvaliacao;
        this.fimExperiencia = fimExperiencia;
        this.estatusAvaliacao = estatusAvaliacao;
        this.tempoAtual = tempoAtual;
        this.salarioAtual = salarioAtual;
        this.salarioProposto = salarioProposto;
        this.exames = exames;
        this.condicao = condicao;
        this.treinamentos = treinamentos;
        this.dataSup = dataSup;
        this.situacao = situacao;
        this.parecerSup = parecerSup;
        this.dataGen = dataGen;
        this.parecerGen = parecerGen;
        this.dataRh = dataRh;
        this.parecerRh = parecerRh;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public Date getDataExames() {
        return dataExames;
    }

    public Date getDataTreinamento() {
        return dataTreinamento;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public Date getFimExperiencia() {
        return fimExperiencia;
    }

    public String getEstatusAvaliacao() {
        return estatusAvaliacao;
    }

    public String getTempoAtual() {
        return tempoAtual;
    }

    public Double getSalarioAtual() {
        return salarioAtual;
    }

    public Double getSalarioProposto() {
        return salarioProposto;
    }

    public String getExames() {
        return exames;
    }

    public String getCondicao() {
        return condicao;
    }

    public String getTreinamentos() {
        return treinamentos;
    }

    public Date getDataSup() {
        return dataSup;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getParecerSup() {
        return parecerSup;
    }

    public Date getDataGen() {
        return dataGen;
    }

    public String getParecerGen() {
        return parecerGen;
    }

    public Date getDataRh() {
        return dataRh;
    }

    public String getParecerRh() {
        return parecerRh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoDto entity = (AvaliacaoDto) o;
        return Objects.equals(this.idAvaliacao, entity.idAvaliacao) &&
                Objects.equals(this.dataExames, entity.dataExames) &&
                Objects.equals(this.dataTreinamento, entity.dataTreinamento) &&
                Objects.equals(this.dataAvaliacao, entity.dataAvaliacao) &&
                Objects.equals(this.fimExperiencia, entity.fimExperiencia) &&
                Objects.equals(this.estatusAvaliacao, entity.estatusAvaliacao) &&
                Objects.equals(this.tempoAtual, entity.tempoAtual) &&
                Objects.equals(this.salarioAtual, entity.salarioAtual) &&
                Objects.equals(this.salarioProposto, entity.salarioProposto) &&
                Objects.equals(this.exames, entity.exames) &&
                Objects.equals(this.condicao, entity.condicao) &&
                Objects.equals(this.treinamentos, entity.treinamentos) &&
                Objects.equals(this.dataSup, entity.dataSup) &&
                Objects.equals(this.situacao, entity.situacao) &&
                Objects.equals(this.parecerSup, entity.parecerSup) &&
                Objects.equals(this.dataGen, entity.dataGen) &&
                Objects.equals(this.parecerGen, entity.parecerGen) &&
                Objects.equals(this.dataRh, entity.dataRh) &&
                Objects.equals(this.parecerRh, entity.parecerRh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAvaliacao, dataExames, dataTreinamento, dataAvaliacao, fimExperiencia, estatusAvaliacao, tempoAtual, salarioAtual, salarioProposto, exames, condicao, treinamentos, dataSup, situacao, parecerSup, dataGen, parecerGen, dataRh, parecerRh);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idAvaliacao = " + idAvaliacao + ", " +
                "dataExames = " + dataExames + ", " +
                "dataTreinamento = " + dataTreinamento + ", " +
                "dataAvaliacao = " + dataAvaliacao + ", " +
                "fimExperiencia = " + fimExperiencia + ", " +
                "estatusAvaliacao = " + estatusAvaliacao + ", " +
                "tempoAtual = " + tempoAtual + ", " +
                "salarioAtual = " + salarioAtual + ", " +
                "salarioProposto = " + salarioProposto + ", " +
                "exames = " + exames + ", " +
                "condicao = " + condicao + ", " +
                "treinamentos = " + treinamentos + ", " +
                "dataSup = " + dataSup + ", " +
                "situacao = " + situacao + ", " +
                "parecerSup = " + parecerSup + ", " +
                "dataGen = " + dataGen + ", " +
                "parecerGen = " + parecerGen + ", " +
                "dataRh = " + dataRh + ", " +
                "parecerRh = " + parecerRh + ")";
    }
}
