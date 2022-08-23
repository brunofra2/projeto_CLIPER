package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PastaDto implements Serializable {
    private final Long id;
    private final int numero;
    private final String armazenamento;
    private final Date dataDescarte;
    private final String descarte;
    private final List<TreinamentoDto> treinamentoList;

    public PastaDto(Long id, int numero, String armazenamento, Date dataDescarte, String descarte, List<TreinamentoDto> treinamentoList) {
        this.id = id;
        this.numero = numero;
        this.armazenamento = armazenamento;
        this.dataDescarte = dataDescarte;
        this.descarte = descarte;
        this.treinamentoList = treinamentoList;
    }

    public Long getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public Date getDataDescarte() {
        return dataDescarte;
    }

    public String getDescarte() {
        return descarte;
    }

    public List<TreinamentoDto> getTreinamentoList() {
        return treinamentoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastaDto entity = (PastaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.numero, entity.numero) &&
                Objects.equals(this.armazenamento, entity.armazenamento) &&
                Objects.equals(this.dataDescarte, entity.dataDescarte) &&
                Objects.equals(this.descarte, entity.descarte) &&
                Objects.equals(this.treinamentoList, entity.treinamentoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, armazenamento, dataDescarte, descarte, treinamentoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "numero = " + numero + ", " +
                "armazenamento = " + armazenamento + ", " +
                "dataDescarte = " + dataDescarte + ", " +
                "descarte = " + descarte + ", " +
                "treinamentoList = " + treinamentoList + ")";
    }
}
