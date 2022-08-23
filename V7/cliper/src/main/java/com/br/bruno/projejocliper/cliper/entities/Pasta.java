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
@Table(name = "pasta")
public class Pasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int numero;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String armazenamento;
    @Column(name = "data_descarte")
    @Temporal(TemporalType.DATE)
    private Date dataDescarte;
    @Column(length = 50)
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
