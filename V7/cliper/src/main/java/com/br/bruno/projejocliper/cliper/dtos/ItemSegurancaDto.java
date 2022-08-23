package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class ItemSegurancaDto implements Serializable {
    private final Integer id;
    private final String epc;
    private final String epi;
    private final String ca;
    private final FuncaoDto idCargo;

    public ItemSegurancaDto(Integer id, String epc, String epi, String ca, FuncaoDto idCargo) {
        this.id = id;
        this.epc = epc;
        this.epi = epi;
        this.ca = ca;
        this.idCargo = idCargo;
    }

    public Integer getId() {
        return id;
    }

    public String getEpc() {
        return epc;
    }

    public String getEpi() {
        return epi;
    }

    public String getCa() {
        return ca;
    }

    public FuncaoDto getIdCargo() {
        return idCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSegurancaDto entity = (ItemSegurancaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.epc, entity.epc) &&
                Objects.equals(this.epi, entity.epi) &&
                Objects.equals(this.ca, entity.ca) &&
                Objects.equals(this.idCargo, entity.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, epc, epi, ca, idCargo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "epc = " + epc + ", " +
                "epi = " + epi + ", " +
                "ca = " + ca + ", " +
                "idCargo = " + idCargo + ")";
    }
}
