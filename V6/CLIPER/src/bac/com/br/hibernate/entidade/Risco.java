/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "risco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Risco.findAll", query = "SELECT r FROM Risco r")
    , @NamedQuery(name = "Risco.findByIdRisco", query = "SELECT r FROM Risco r WHERE r.idRisco = :idRisco")
    , @NamedQuery(name = "Risco.findByFatorDeRisco", query = "SELECT r FROM Risco r WHERE r.fatorDeRisco = :fatorDeRisco")
    , @NamedQuery(name = "Risco.findByClassificacao", query = "SELECT r FROM Risco r WHERE r.classificacao = :classificacao")
    , @NamedQuery(name = "Risco.findByCategoria", query = "SELECT r FROM Risco r WHERE r.categoria = :categoria")
    , @NamedQuery(name = "Risco.findByIntencidade", query = "SELECT r FROM Risco r WHERE r.intencidade = :intencidade")})
public class Risco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_risco")
    private Integer idRisco;
    @Basic(optional = false)
    @Column(name = "fator_de_risco")
    private String fatorDeRisco;
    @Basic(optional = false)
    @Column(name = "classificacao")
    private String classificacao;
    @Basic(optional = false)
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "intencidade")
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
