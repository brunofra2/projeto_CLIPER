/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "risco")
public class Risco  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_risco", nullable = false)
    private Integer idRisco;
    @Basic(optional = false)
    @Column(name = "fator_de_risco", nullable = false, length = 50)
    private String fatorDeRisco;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String classificacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String categoria;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String intencidade;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idCargo;

    public Risco() {
    }

    public Risco(Integer idRisco) {
        this.idRisco = idRisco;
    }

    public Risco(Integer idRisco, String fatorDeRisco, String classificacao, String categoria, String intencidade) {
        this.idRisco = idRisco;
        this.fatorDeRisco = fatorDeRisco;
        this.classificacao = classificacao;
        this.categoria = categoria;
        this.intencidade = intencidade;
    }

    public Integer getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Integer idRisco) {
        this.idRisco = idRisco;
    }

    public String getFatorDeRisco() {
        return fatorDeRisco;
    }

    public void setFatorDeRisco(String fatorDeRisco) {
        this.fatorDeRisco = fatorDeRisco;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIntencidade() {
        return intencidade;
    }

    public void setIntencidade(String intencidade) {
        this.intencidade = intencidade;
    }

    public Funcao getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Funcao idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRisco != null ? idRisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Risco)) {
            return false;
        }
        Risco other = (Risco) object;
        if ((this.idRisco == null && other.idRisco != null) || (this.idRisco != null && !this.idRisco.equals(other.idRisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Risco[ idRisco=" + idRisco + " ]";
    }
    
}
