/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author bruno
 */
@Entity
@Table(name = "descricao")
public class Descricao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String area;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao_detalhada", nullable = false, length = 65535)
    private String descricaoDetalhada;
    @Basic(optional = false)
    @Column(name = "escolaridade_des", nullable = false, length = 100)
    private String escolaridadeDes;
    @Basic(optional = false)
    @Column(name = "escolaridade_min", nullable = false, length = 100)
    private String escolaridadeMin;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String experiencia;
    @Basic(optional = false)
    @Column(name = "faixa_etaria", nullable = false, length = 100)
    private String faixaEtaria;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String habilidade;
    @Basic(optional = false)
    @Column(name = "habilitacao_profissional", nullable = false, length = 100)
    private String habilitacaoProfissional;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String lidera;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String missao;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String responsabilidades;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String sexo;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String supervisao;
    @Basic(optional = false)
    @Lob
    @Column(name = "treinamento_legais", nullable = false, length = 65535)
    private String treinamentoLegais;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String integracao;
    @JoinTable(name = "descricao_documento", joinColumns = {
        @JoinColumn(name = "descricao_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_documento_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "idDescricao", fetch = FetchType.LAZY)
    private List<Historico> historicoList;
    @JoinColumn(name = "id_funcao_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idFuncaoId;
    @JoinColumn(name = "id_setor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor idSetorId;

    public Descricao() {
    }

    public Descricao(Long id) {
        this.id = id;
    }

    public Descricao(Long id, String area, String descricaoDetalhada, String escolaridadeDes, String escolaridadeMin, String experiencia, String faixaEtaria, String habilidade, String habilitacaoProfissional, String lidera, String missao, String responsabilidades, String sexo, String supervisao, String treinamentoLegais, String integracao) {
        this.id = id;
        this.area = area;
        this.descricaoDetalhada = descricaoDetalhada;
        this.escolaridadeDes = escolaridadeDes;
        this.escolaridadeMin = escolaridadeMin;
        this.experiencia = experiencia;
        this.faixaEtaria = faixaEtaria;
        this.habilidade = habilidade;
        this.habilitacaoProfissional = habilitacaoProfissional;
        this.lidera = lidera;
        this.missao = missao;
        this.responsabilidades = responsabilidades;
        this.sexo = sexo;
        this.supervisao = supervisao;
        this.treinamentoLegais = treinamentoLegais;
        this.integracao = integracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getEscolaridadeDes() {
        return escolaridadeDes;
    }

    public void setEscolaridadeDes(String escolaridadeDes) {
        this.escolaridadeDes = escolaridadeDes;
    }

    public String getEscolaridadeMin() {
        return escolaridadeMin;
    }

    public void setEscolaridadeMin(String escolaridadeMin) {
        this.escolaridadeMin = escolaridadeMin;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getHabilitacaoProfissional() {
        return habilitacaoProfissional;
    }

    public void setHabilitacaoProfissional(String habilitacaoProfissional) {
        this.habilitacaoProfissional = habilitacaoProfissional;
    }

    public String getLidera() {
        return lidera;
    }

    public void setLidera(String lidera) {
        this.lidera = lidera;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getResponsabilidades() {
        return responsabilidades;
    }

    public void setResponsabilidades(String responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(String supervisao) {
        this.supervisao = supervisao;
    }

    public String getTreinamentoLegais() {
        return treinamentoLegais;
    }

    public void setTreinamentoLegais(String treinamentoLegais) {
        this.treinamentoLegais = treinamentoLegais;
    }

    public String getIntegracao() {
        return integracao;
    }

    public void setIntegracao(String integracao) {
        this.integracao = integracao;
    }


    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }


    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    public Funcao getIdFuncaoId() {
        return idFuncaoId;
    }

    public void setIdFuncaoId(Funcao idFuncaoId) {
        this.idFuncaoId = idFuncaoId;
    }

    public Setor getIdSetorId() {
        return idSetorId;
    }

    public void setIdSetorId(Setor idSetorId) {
        this.idSetorId = idSetorId;
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
        if (!(object instanceof Descricao)) {
            return false;
        }
        Descricao other = (Descricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Descricao[ id=" + id + " ]";
    }
    
}
