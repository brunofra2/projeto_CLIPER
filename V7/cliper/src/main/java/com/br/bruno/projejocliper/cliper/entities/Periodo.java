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
@Table(name = "periodo")
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Column(length = 20)
    private String periodo;
    @JoinTable(name = "itens_periodo", joinColumns = {
        @JoinColumn(name = "periodo_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "Itens_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Itens> itensList;
    @JoinColumn(name = "iten_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Itens itenId;

    public Periodo() {
    }

    public Periodo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Itens> getItensList() {
        return itensList;
    }

    public void setItensList(List<Itens> itensList) {
        this.itensList = itensList;
    }

    public Itens getItenId() {
        return itenId;
    }

    public void setItenId(Itens itenId) {
        this.itenId = itenId;
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
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Periodo[ id=" + id + " ]";
    }
    
}
