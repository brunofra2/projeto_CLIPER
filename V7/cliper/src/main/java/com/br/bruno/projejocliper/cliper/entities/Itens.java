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
@Table(name = "itens")
public class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String descricao;
    @Column(name = "mes_fim", length = 20)
    private String mesFim;
    @Column(name = "mes_inicio", length = 20)
    private String mesInicio;
    @ManyToMany(mappedBy = "itensList", fetch = FetchType.EAGER)
    private List<Periodo> periodoList;
    @JoinColumn(name = "pgr_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pgr pgrId;
    @OneToMany(mappedBy = "itenId", fetch = FetchType.LAZY)
    private List<Periodo> periodoList1;
    @OneToMany(mappedBy = "itensId", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;

    public Itens() {
    }

    public Itens(Long id) {
        this.id = id;
    }

    public Itens(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMesFim() {
        return mesFim;
    }

    public void setMesFim(String mesFim) {
        this.mesFim = mesFim;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    public List<Periodo> getPeriodoList() {
        return periodoList;
    }

    public void setPeriodoList(List<Periodo> periodoList) {
        this.periodoList = periodoList;
    }

    public Pgr getPgrId() {
        return pgrId;
    }

    public void setPgrId(Pgr pgrId) {
        this.pgrId = pgrId;
    }

    public List<Periodo> getPeriodoList1() {
        return periodoList1;
    }

    public void setPeriodoList1(List<Periodo> periodoList1) {
        this.periodoList1 = periodoList1;
    }

    public List<Treinamento> getTreinamentoList() {
        return treinamentoList;
    }

    public void setTreinamentoList(List<Treinamento> treinamentoList) {
        this.treinamentoList = treinamentoList;
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
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Itens[ id=" + id + " ]";
    }
    
}
