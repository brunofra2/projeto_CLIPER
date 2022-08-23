package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ColaboradorDto implements Serializable {
    private final Long id;
    private final Date dataAdm;
    private final Date dataNasc;
    private final String nome;
    private final String registro;
    private final String status;
    private final String pis;
    private final String sexo;
    private final String ctps;
    private final String serie;
    private final String estado;
    private final List<TreinamentoDto> treinamentoList;
    private final List<HistoricoDto> historicoList;
    private final FuncaoDto funcaoId;
    private final List<CursoDto> cursoList;

    public ColaboradorDto(Long id, Date dataAdm, Date dataNasc, String nome, String registro, String status, String pis, String sexo, String ctps, String serie, String estado, List<TreinamentoDto> treinamentoList, List<HistoricoDto> historicoList, FuncaoDto funcaoId, List<CursoDto> cursoList) {
        this.id = id;
        this.dataAdm = dataAdm;
        this.dataNasc = dataNasc;
        this.nome = nome;
        this.registro = registro;
        this.status = status;
        this.pis = pis;
        this.sexo = sexo;
        this.ctps = ctps;
        this.serie = serie;
        this.estado = estado;
        this.treinamentoList = treinamentoList;
        this.historicoList = historicoList;
        this.funcaoId = funcaoId;
        this.cursoList = cursoList;
    }

    public Long getId() {
        return id;
    }

    public Date getDataAdm() {
        return dataAdm;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistro() {
        return registro;
    }

    public String getStatus() {
        return status;
    }

    public String getPis() {
        return pis;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCtps() {
        return ctps;
    }

    public String getSerie() {
        return serie;
    }

    public String getEstado() {
        return estado;
    }

    public List<TreinamentoDto> getTreinamentoList() {
        return treinamentoList;
    }

    public List<HistoricoDto> getHistoricoList() {
        return historicoList;
    }

    public FuncaoDto getFuncaoId() {
        return funcaoId;
    }

    public List<CursoDto> getCursoList() {
        return cursoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColaboradorDto entity = (ColaboradorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dataAdm, entity.dataAdm) &&
                Objects.equals(this.dataNasc, entity.dataNasc) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.registro, entity.registro) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.pis, entity.pis) &&
                Objects.equals(this.sexo, entity.sexo) &&
                Objects.equals(this.ctps, entity.ctps) &&
                Objects.equals(this.serie, entity.serie) &&
                Objects.equals(this.estado, entity.estado) &&
                Objects.equals(this.treinamentoList, entity.treinamentoList) &&
                Objects.equals(this.historicoList, entity.historicoList) &&
                Objects.equals(this.funcaoId, entity.funcaoId) &&
                Objects.equals(this.cursoList, entity.cursoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAdm, dataNasc, nome, registro, status, pis, sexo, ctps, serie, estado, treinamentoList, historicoList, funcaoId, cursoList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dataAdm = " + dataAdm + ", " +
                "dataNasc = " + dataNasc + ", " +
                "nome = " + nome + ", " +
                "registro = " + registro + ", " +
                "status = " + status + ", " +
                "pis = " + pis + ", " +
                "sexo = " + sexo + ", " +
                "ctps = " + ctps + ", " +
                "serie = " + serie + ", " +
                "estado = " + estado + ", " +
                "treinamentoList = " + treinamentoList + ", " +
                "historicoList = " + historicoList + ", " +
                "funcaoId = " + funcaoId + ", " +
                "cursoList = " + cursoList + ")";
    }
}
