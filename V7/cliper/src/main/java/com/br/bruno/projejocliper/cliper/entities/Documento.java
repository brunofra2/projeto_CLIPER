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
@Table(name = "documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String titulo;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String tipo;
    @Column(length = 50)
    private String modificacao;
    @ManyToMany(mappedBy = "documentoList", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;
    @ManyToMany(mappedBy = "documentoList", fetch = FetchType.LAZY)
    private List<Descricao> descricaoList;
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor setorId;
    @JoinColumn(name = "tipo_treinamento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTreinamento tipoTreinamentoId;

    public Documento() {
    }

    public Documento(Long id) {
        this.id = id;
    }

    public Documento(Long id, String titulo, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModificacao() {
        return modificacao;
    }

    public void setModificacao(String modificacao) {
        this.modificacao = modificacao;
    }

    public List<Treinamento> getTreinamentoList() {
        return treinamentoList;
    }

    public void setTreinamentoList(List<Treinamento> treinamentoList) {
        this.treinamentoList = treinamentoList;
    }

    public List<Descricao> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<Descricao> descricaoList) {
        this.descricaoList = descricaoList;
    }

    public Setor getSetorId() {
        return setorId;
    }

    public void setSetorId(Setor setorId) {
        this.setorId = setorId;
    }

    public TipoTreinamento getTipoTreinamentoId() {
        return tipoTreinamentoId;
    }

    public void setTipoTreinamentoId(TipoTreinamento tipoTreinamentoId) {
        this.tipoTreinamentoId = tipoTreinamentoId;
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
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Documento[ id=" + id + " ]";
    }
    
}
