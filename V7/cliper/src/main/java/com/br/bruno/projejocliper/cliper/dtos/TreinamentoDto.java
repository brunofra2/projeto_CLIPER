package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TreinamentoDto implements Serializable {
    private final Long id;
    private final Date dataTreinamento;
    private final Date dataFinalizacao;
    private final String horarioFinal;
    private final String horarioInicio;
    private final String localidade;
    private final Date proxTreinamento;
    private final String status;
    private final String tipo;
    private final String prorrogacao;

    public TreinamentoDto(Long id, Date dataTreinamento, Date dataFinalizacao, String horarioFinal, String horarioInicio, String localidade, Date proxTreinamento, String status, String tipo, String prorrogacao) {
        this.id = id;
        this.dataTreinamento = dataTreinamento;
        this.dataFinalizacao = dataFinalizacao;
        this.horarioFinal = horarioFinal;
        this.horarioInicio = horarioInicio;
        this.localidade = localidade;
        this.proxTreinamento = proxTreinamento;
        this.status = status;
        this.tipo = tipo;
        this.prorrogacao = prorrogacao;
    }

    public Long getId() {
        return id;
    }

    public Date getDataTreinamento() {
        return dataTreinamento;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getLocalidade() {
        return localidade;
    }

    public Date getProxTreinamento() {
        return proxTreinamento;
    }

    public String getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }

    public String getProrrogacao() {
        return prorrogacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreinamentoDto entity = (TreinamentoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dataTreinamento, entity.dataTreinamento) &&
                Objects.equals(this.dataFinalizacao, entity.dataFinalizacao) &&
                Objects.equals(this.horarioFinal, entity.horarioFinal) &&
                Objects.equals(this.horarioInicio, entity.horarioInicio) &&
                Objects.equals(this.localidade, entity.localidade) &&
                Objects.equals(this.proxTreinamento, entity.proxTreinamento) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.tipo, entity.tipo) &&
                Objects.equals(this.prorrogacao, entity.prorrogacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataTreinamento, dataFinalizacao, horarioFinal, horarioInicio, localidade, proxTreinamento, status, tipo, prorrogacao);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dataTreinamento = " + dataTreinamento + ", " +
                "dataFinalizacao = " + dataFinalizacao + ", " +
                "horarioFinal = " + horarioFinal + ", " +
                "horarioInicio = " + horarioInicio + ", " +
                "localidade = " + localidade + ", " +
                "proxTreinamento = " + proxTreinamento + ", " +
                "status = " + status + ", " +
                "tipo = " + tipo + ", " +
                "prorrogacao = " + prorrogacao + ")";
    }
}
