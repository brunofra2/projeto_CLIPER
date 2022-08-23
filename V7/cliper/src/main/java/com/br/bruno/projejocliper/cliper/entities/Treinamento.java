/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "treinamento")
public class Treinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Column(name = "data_treinamento")
    @Temporal(TemporalType.DATE)
    private Date dataTreinamento;
    @Column(name = "data_finalizacao")
    @Temporal(TemporalType.DATE)
    private Date dataFinalizacao;
    @Basic(optional = false)
    @Column(name = "horario_final", nullable = false, length = 50)
    private String horarioFinal;
    @Basic(optional = false)
    @Column(name = "horario_inicio", nullable = false, length = 50)
    private String horarioInicio;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String localidade;
    @Basic(optional = false)
    @Column(name = "prox_treinamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date proxTreinamento;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String status;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String tipo;
    @Lob
    @Column(length = 65535)
    private String prorrogacao;
    @JoinTable(name = "treinamento_documento", joinColumns = {
        @JoinColumn(name = "Treinamento_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "documento_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @JoinTable(name = "treinamento_colaborador", joinColumns = {
        @JoinColumn(name = "Treinamento_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Colaborador> colaboradorList;
    @JoinColumn(name = "tipo_treinamento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTreinamento tipoTreinamentoId;
    @JoinColumn(name = "pasta_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pasta pastaId;
    @JoinColumn(name = "itens_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Itens itensId;
    @JoinColumn(name = "treinador_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Treinador treinadorId;

    public Treinamento() {
    }

    public Treinamento(Long id) {
        this.id = id;
    }

    public Treinamento(Long id, String horarioFinal, String horarioInicio, String localidade, Date proxTreinamento, String status, String tipo) {
        this.id = id;
        this.horarioFinal = horarioFinal;
        this.horarioInicio = horarioInicio;
        this.localidade = localidade;
        this.proxTreinamento = proxTreinamento;
        this.status = status;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTreinamento() {
        return dataTreinamento;
    }

    public void setDataTreinamento(Date dataTreinamento) {
        this.dataTreinamento = dataTreinamento;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Date getProxTreinamento() {
        return proxTreinamento;
    }

    public void setProxTreinamento(Date proxTreinamento) {
        this.proxTreinamento = proxTreinamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProrrogacao() {
        return prorrogacao;
    }

    public void setProrrogacao(String prorrogacao) {
        this.prorrogacao = prorrogacao;
    }


    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<Colaborador> getColaboradorList() {
        return colaboradorList;
    }

    public void setColaboradorList(List<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }

    public TipoTreinamento getTipoTreinamentoId() {
        return tipoTreinamentoId;
    }

    public void setTipoTreinamentoId(TipoTreinamento tipoTreinamentoId) {
        this.tipoTreinamentoId = tipoTreinamentoId;
    }

    public Pasta getPastaId() {
        return pastaId;
    }

    public void setPastaId(Pasta pastaId) {
        this.pastaId = pastaId;
    }

    public Itens getItensId() {
        return itensId;
    }

    public void setItensId(Itens itensId) {
        this.itensId = itensId;
    }

    public Treinador getTreinadorId() {
        return treinadorId;
    }

    public void setTreinadorId(Treinador treinadorId) {
        this.treinadorId = treinadorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treinamento)) {
            return false;
        }
        Treinamento other = (Treinamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Treinamento[ id=" + id + " ]";
    }
    
}
