package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TipoTreinamentoDto implements Serializable {
    private final Long id;
    private final int periodo;
    private final String tipoTreinamento;
    private final List<DocumentoDto> documentoList;
    private final List<TreinamentoDto> treinamentoList;

    public TipoTreinamentoDto(Long id, int periodo, String tipoTreinamento, List<DocumentoDto> documentoList, List<TreinamentoDto> treinamentoList) {
        this.id = id;
        this.periodo = periodo;
        this.tipoTreinamento = tipoTreinamento;
        this.documentoList = documentoList;
        this.treinamentoList = treinamentoList;
    }

    public Long getId() {
        return id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public String getTipoTreinamento() {
        return tipoTreinamento;
    }

    public List<DocumentoDto> getDocumentoList() {
        return documentoList;
    }

    public List<TreinamentoDto> getTreinamentoList() {
        return treinamentoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoTreinamentoDto entity = (TipoTreinamentoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.periodo, entity.periodo) &&
                Objects.equals(this.tipoTreinamento, entity.tipoTreinamento) &&
                Objects.equals(this.documentoList, entity.documentoList) &&
                Objects.equals(this.treinamentoList, entity.treinamentoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodo, tipoTreinamento, documentoList, treinamentoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "periodo = " + periodo + ", " +
                "tipoTreinamento = " + tipoTreinamento + ", " +
                "documentoList = " + documentoList + ", " +
                "treinamentoList = " + treinamentoList + ")";
    }
}
