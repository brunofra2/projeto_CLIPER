package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class EmpresaDto implements Serializable {
    private final Long id;
    private final String cnpj;
    private final String nome;
    private final List<TreinadorDto> treinadorList;

    public EmpresaDto(Long id, String cnpj, String nome, List<TreinadorDto> treinadorList) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.treinadorList = treinadorList;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public List<TreinadorDto> getTreinadorList() {
        return treinadorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaDto entity = (EmpresaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.cnpj, entity.cnpj) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.treinadorList, entity.treinadorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, nome, treinadorList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "cnpj = " + cnpj + ", " +
                "nome = " + nome + ", " +
                "treinadorList = " + treinadorList + ")";
    }
}
