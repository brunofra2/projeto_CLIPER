package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DocumentoDto implements Serializable {
    private final Long id;
    private final String titulo;
    private final String tipo;
    private final String modificacao;
    private final List<TreinamentoDto> treinamentoList;
    private final List<DescricaoDto> descricaoList;
    private final SetorDto setorId;
    private final TipoTreinamentoDto tipoTreinamentoId;

    public DocumentoDto(Long id, String titulo, String tipo, String modificacao, List<TreinamentoDto> treinamentoList, List<DescricaoDto> descricaoList, SetorDto setorId, TipoTreinamentoDto tipoTreinamentoId) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.modificacao = modificacao;
        this.treinamentoList = treinamentoList;
        this.descricaoList = descricaoList;
        this.setorId = setorId;
        this.tipoTreinamentoId = tipoTreinamentoId;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getModificacao() {
        return modificacao;
    }

    public List<TreinamentoDto> getTreinamentoList() {
        return treinamentoList;
    }

    public List<DescricaoDto> getDescricaoList() {
        return descricaoList;
    }

    public SetorDto getSetorId() {
        return setorId;
    }

    public TipoTreinamentoDto getTipoTreinamentoId() {
        return tipoTreinamentoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentoDto entity = (DocumentoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.titulo, entity.titulo) &&
                Objects.equals(this.tipo, entity.tipo) &&
                Objects.equals(this.modificacao, entity.modificacao) &&
                Objects.equals(this.treinamentoList, entity.treinamentoList) &&
                Objects.equals(this.descricaoList, entity.descricaoList) &&
                Objects.equals(this.setorId, entity.setorId) &&
                Objects.equals(this.tipoTreinamentoId, entity.tipoTreinamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, tipo, modificacao, treinamentoList, descricaoList, setorId, tipoTreinamentoId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "titulo = " + titulo + ", " +
                "tipo = " + tipo + ", " +
                "modificacao = " + modificacao + ", " +
                "treinamentoList = " + treinamentoList + ", " +
                "descricaoList = " + descricaoList + ", " +
                "setorId = " + setorId + ", " +
                "tipoTreinamentoId = " + tipoTreinamentoId + ")";
    }
}
