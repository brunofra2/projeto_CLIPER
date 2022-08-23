package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ItensDto implements Serializable {
    private final Long id;
    private final String descricao;
    private final String mesFim;
    private final String mesInicio;
    private final List<PeriodoDto> periodoList;
    private final PgrDto pgrId;
    private final List<PeriodoDto> periodoList1;
    private final List<TreinamentoDto> treinamentoList;

    public ItensDto(Long id, String descricao, String mesFim, String mesInicio, List<PeriodoDto> periodoList, PgrDto pgrId, List<PeriodoDto> periodoList1, List<TreinamentoDto> treinamentoList) {
        this.id = id;
        this.descricao = descricao;
        this.mesFim = mesFim;
        this.mesInicio = mesInicio;
        this.periodoList = periodoList;
        this.pgrId = pgrId;
        this.periodoList1 = periodoList1;
        this.treinamentoList = treinamentoList;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMesFim() {
        return mesFim;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public List<PeriodoDto> getPeriodoList() {
        return periodoList;
    }

    public PgrDto getPgrId() {
        return pgrId;
    }

    public List<PeriodoDto> getPeriodoList1() {
        return periodoList1;
    }

    public List<TreinamentoDto> getTreinamentoList() {
        return treinamentoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensDto entity = (ItensDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.descricao, entity.descricao) &&
                Objects.equals(this.mesFim, entity.mesFim) &&
                Objects.equals(this.mesInicio, entity.mesInicio) &&
                Objects.equals(this.periodoList, entity.periodoList) &&
                Objects.equals(this.pgrId, entity.pgrId) &&
                Objects.equals(this.periodoList1, entity.periodoList1) &&
                Objects.equals(this.treinamentoList, entity.treinamentoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, mesFim, mesInicio, periodoList, pgrId, periodoList1, treinamentoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "descricao = " + descricao + ", " +
                "mesFim = " + mesFim + ", " +
                "mesInicio = " + mesInicio + ", " +
                "periodoList = " + periodoList + ", " +
                "pgrId = " + pgrId + ", " +
                "periodoList1 = " + periodoList1 + ", " +
                "treinamentoList = " + treinamentoList + ")";
    }
}
