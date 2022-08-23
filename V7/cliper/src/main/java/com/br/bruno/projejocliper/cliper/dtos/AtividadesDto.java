package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class AtividadesDto implements Serializable {
    private final Long id;
    private final String descricao;
    private final String complemento;
    private final FuncaoDto idCargo;

    public AtividadesDto(Long id, String descricao, String complemento, FuncaoDto idCargo) {
        this.id = id;
        this.descricao = descricao;
        this.complemento = complemento;
        this.idCargo = idCargo;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public FuncaoDto getIdCargo() {
        return idCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtividadesDto entity = (AtividadesDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.descricao, entity.descricao) &&
                Objects.equals(this.complemento, entity.complemento) &&
                Objects.equals(this.idCargo, entity.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, complemento, idCargo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "descricao = " + descricao + ", " +
                "complemento = " + complemento + ", " +
                "idCargo = " + idCargo + ")";
    }
}
