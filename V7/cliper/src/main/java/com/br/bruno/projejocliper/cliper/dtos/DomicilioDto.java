package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DomicilioDto implements Serializable {
    private Integer idDomicilio;
    private String nome;
    private String cnpj;
    private String cnae;
    private List<FuncaoDto> funcaoList;
    private List<LotacaoDto> lotacaoList;

    public DomicilioDto() {
    }

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

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public List<FuncaoDto> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<FuncaoDto> funcaoList) {
        this.funcaoList = funcaoList;
    }

    public List<LotacaoDto> getLotacaoList() {
        return lotacaoList;
    }

    public void setLotacaoList(List<LotacaoDto> lotacaoList) {
        this.lotacaoList = lotacaoList;
    }
}
