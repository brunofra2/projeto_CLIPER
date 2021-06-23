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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id")
    , @NamedQuery(name = "Documento.findByTitulo", query = "SELECT d FROM Documento d WHERE d.titulo = :titulo")
    , @NamedQuery(name = "Documento.findByTipo", query = "SELECT d FROM Documento d WHERE d.tipo = :tipo")
    , @NamedQuery(name = "Documento.findByModificacao", query = "SELECT d FROM Documento d WHERE d.modificacao = :modificacao")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "modificacao")
    private String modificacao;
    @ManyToMany(mappedBy = "documentoList", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;
    @ManyToMany(mappedBy = "documentoList", fetch = FetchType.LAZY)
    private List<Descricao> descricaoList;
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor setorId;
    @JoinColumn(name = "tipo_treinamento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTreinamento tipoTreinamentoId;

    public Documento() {
    }

    public Documento(Long id) {
        this.id = id;
    }

    public Documento(Long id, String titulo, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModificacao() {
        return modificacao;
    }

    public void setModificacao(String modificacao) {
        this.modificacao = modificacao;
    }

    @XmlTransient
    public List<Treinamento> getTreinamentoList() {
        return treinamentoList;
    }

    public void setTreinamentoList(List<Treinamento> treinamentoList) {
        this.treinamentoList = treinamentoList;
    }

    @XmlTransient
    public List<Descricao> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<Descricao> descricaoList) {
        this.descricaoList = descricaoList;
    }

    public Setor getSetorId() {
        return setorId;
    }

    public void setSetorId(Setor setorId) {
        this.setorId = setorId;
    }

    public TipoTreinamento getTipoTreinamentoId() {
        return tipoTreinamentoId;
    }

    public void setTipoTreinamentoId(TipoTreinamento tipoTreinamentoId) {
        this.tipoTreinamentoId = tipoTreinamentoId;
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
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Documento[ id=" + id + " ]";
    }
    
}
