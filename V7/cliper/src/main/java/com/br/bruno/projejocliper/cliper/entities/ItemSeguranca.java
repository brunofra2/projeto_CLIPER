package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "item_seguranca")
public class ItemSeguranca {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 50)
    private String epc;
    @Column(length = 50)
    private String epi;
    @Column(length = 50)
    private String ca;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Funcao idCargo;

    public ItemSeguranca() {
    }

    public ItemSeguranca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getEpi() {
        return epi;
    }

    public void setEpi(String epi) {
        this.epi = epi;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSeguranca)) {
            return false;
        }
        ItemSeguranca other = (ItemSeguranca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.ItemSeguranca[ id=" + id + " ]";
    }
    
}
