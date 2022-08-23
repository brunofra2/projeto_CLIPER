package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SetorDto implements Serializable {
    private final Long id;
    private final String responsavel;
    private final String email;
    private final String situacao;
    private final String nomeSetor;
    private final List<FuncaoDto> funcaoList;
    private final List<DocumentoDto> documentoList;

    public SetorDto(Long id, String responsavel, String email, String situacao, String nomeSetor, List<FuncaoDto> funcaoList, List<DocumentoDto> documentoList) {
        this.id = id;
        this.responsavel = responsavel;
        this.email = email;
        this.situacao = situacao;
        this.nomeSetor = nomeSetor;
        this.funcaoList = funcaoList;
        this.documentoList = documentoList;
    }

    public Long getId() {
        return id;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getEmail() {
        return email;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public List<FuncaoDto> getFuncaoList() {
        return funcaoList;
    }

    public List<DocumentoDto> getDocumentoList() {
        return documentoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetorDto entity = (SetorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.responsavel, entity.responsavel) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.situacao, entity.situacao) &&
                Objects.equals(this.nomeSetor, entity.nomeSetor) &&
                Objects.equals(this.funcaoList, entity.funcaoList) &&
                Objects.equals(this.documentoList, entity.documentoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responsavel, email, situacao, nomeSetor, funcaoList, documentoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "responsavel = " + responsavel + ", " +
                "email = " + email + ", " +
                "situacao = " + situacao + ", " +
                "nomeSetor = " + nomeSetor + ", " +
                "funcaoList = " + funcaoList + ", " +
                "documentoList = " + documentoList + ")";
    }
}
