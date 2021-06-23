/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i")
    , @NamedQuery(name = "Itens.findById", query = "SELECT i FROM Itens i WHERE i.id = :id")
    , @NamedQuery(name = "Itens.findByDescricao", query = "SELECT i FROM Itens i WHERE i.descricao = :descricao")
    , @NamedQuery(name = "Itens.findByMesFim", query = "SELECT i FROM Itens i WHERE i.mesFim = :mesFim")
    , @NamedQuery(name = "Itens.findByMesInicio", query = "SELECT i FROM Itens i WHERE i.mesInicio = :mesInicio")})
public class Itens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "mes_fim")
    private String mesFim;
    @Column(name = "mes_inicio")
    private String mesInicio;
    @ManyToMany(mappedBy = "itensList", fetch = FetchType.LAZY)
    private List<Periodo> periodoList;
    @JoinColumn(name = "pgr_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
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

    @XmlTransient
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

    @XmlTransient
    public List<Periodo> getPeriodoList1() {
        return periodoList1;
    }

    public void setPeriodoList1(List<Periodo> periodoList1) {
        this.periodoList1 = periodoList1;
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
