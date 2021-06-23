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
@Table(name = "item_seguranca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemSeguranca.findAll", query = "SELECT i FROM ItemSeguranca i")
    , @NamedQuery(name = "ItemSeguranca.findById", query = "SELECT i FROM ItemSeguranca i WHERE i.id = :id")
    , @NamedQuery(name = "ItemSeguranca.findByEpc", query = "SELECT i FROM ItemSeguranca i WHERE i.epc = :epc")
    , @NamedQuery(name = "ItemSeguranca.findByEpi", query = "SELECT i FROM ItemSeguranca i WHERE i.epi = :epi")
    , @NamedQuery(name = "ItemSeguranca.findByCa", query = "SELECT i FROM ItemSeguranca i WHERE i.ca = :ca")})
public class ItemSeguranca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "EPC")
    private String epc;
    @Column(name = "EPI")
    private String epi;
    @Column(name = "CA")
    private String ca;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
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
