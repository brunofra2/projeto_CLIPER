/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "pasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasta.findAll", query = "SELECT p FROM Pasta p")
    , @NamedQuery(name = "Pasta.findById", query = "SELECT p FROM Pasta p WHERE p.id = :id")
    , @NamedQuery(name = "Pasta.findByNumero", query = "SELECT p FROM Pasta p WHERE p.numero = :numero")
    , @NamedQuery(name = "Pasta.findByArmazenamento", query = "SELECT p FROM Pasta p WHERE p.armazenamento = :armazenamento")
    , @NamedQuery(name = "Pasta.findByDataDescarte", query = "SELECT p FROM Pasta p WHERE p.dataDescarte = :dataDescarte")
    , @NamedQuery(name = "Pasta.findByDescarte", query = "SELECT p FROM Pasta p WHERE p.descarte = :descarte")})
public class Pasta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "armazenamento")
    private String armazenamento;
    @Column(name = "data_descarte")
    @Temporal(TemporalType.DATE)
    private Date dataDescarte;
    @Column(name = "descarte")
    private String descarte;
    @OneToMany(mappedBy = "pastaId", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;

    public Pasta() {
    }

    public Pasta(Long id) {
        this.id = id;
    }

    public Pasta(Long id, int numero, String armazenamento) {
        this.id = id;
        this.numero = numero;
        this.armazenamento = armazenamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public Date getDataDescarte() {
        return dataDescarte;
    }

    public void setDataDescarte(Date dataDescarte) {
        this.dataDescarte = dataDescarte;
    }

    public String getDescarte() {
        return descarte;
    }

    public void setDescarte(String descarte) {
        this.descarte = descarte;
    }

    @XmlTransient
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
        if (!(object instanceof Pasta)) {
            return false;
        }
        Pasta other = (Pasta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Pasta[ id=" + id + " ]";
    }
    
}
