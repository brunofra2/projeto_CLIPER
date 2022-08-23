package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DomicilioDto implements Serializable {
    private final Integer idDomicilio;
    private final String nome;
    private final String cnpj;
    private final String cnae;
    private final List<FuncaoDto> funcaoList;
    private final List<LotacaoDto> lotacaoList;

    public DomicilioDto(Integer idDomicilio, String nome, String cnpj, String cnae, List<FuncaoDto> funcaoList, List<LotacaoDto> lotacaoList) {
        this.idDomicilio = idDomicilio;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cnae = cnae;
        this.funcaoList = funcaoList;
        this.lotacaoList = lotacaoList;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public List<FuncaoDto> getFuncaoList() {
        return funcaoList;
    }

    public List<LotacaoDto> getLotacaoList() {
        return lotacaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomicilioDto entity = (DomicilioDto) o;
        return Objects.equals(this.idDomicilio, entity.idDomicilio) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.cnpj, entity.cnpj) &&
                Objects.equals(this.cnae, entity.cnae) &&
                Objects.equals(this.funcaoList, entity.funcaoList) &&
                Objects.equals(this.lotacaoList, entity.lotacaoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDomicilio, nome, cnpj, cnae, funcaoList, lotacaoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idDomicilio = " + idDomicilio + ", " +
                "nome = " + nome + ", " +
                "cnpj = " + cnpj + ", " +
                "cnae = " + cnae + ", " +
                "funcaoList = " + funcaoList + ", " +
                "lotacaoList = " + lotacaoList + ")";
    }
}
