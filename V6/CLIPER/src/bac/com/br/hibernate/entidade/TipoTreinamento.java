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
@Table(name = "tipo_treinamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTreinamento.findAll", query = "SELECT t FROM TipoTreinamento t")
    , @NamedQuery(name = "TipoTreinamento.findById", query = "SELECT t FROM TipoTreinamento t WHERE t.id = :id")
    , @NamedQuery(name = "TipoTreinamento.findByPeriodo", query = "SELECT t FROM TipoTreinamento t WHERE t.periodo = :periodo")
    , @NamedQuery(name = "TipoTreinamento.findByTipoTreinamento", query = "SELECT t FROM TipoTreinamento t WHERE t.tipoTreinamento = :tipoTreinamento")})
public class TipoTreinamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @Column(name = "tipo_treinamento")
    private String tipoTreinamento;
    @OneToMany(mappedBy = "tipoTreinamentoId", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "tipoTreinamentoId", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;

    public TipoTreinamento() {
    }

    public TipoTreinamento(Long id) {
        this.id = id;
    }

    public TipoTreinamento(Long id, int periodo, String tipoTreinamento) {
        this.id = id;
        this.periodo = periodo;
        this.tipoTreinamento = tipoTreinamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getTipoTreinamento() {
        return tipoTreinamento;
    }

    public void setTipoTreinamento(String tipoTreinamento) {
        this.tipoTreinamento = tipoTreinamento;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
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
        if (!(object instanceof TipoTreinamento)) {
            return false;
        }
        TipoTreinamento other = (TipoTreinamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.TipoTreinamento[ id=" + id + " ]";
    }
    
}
