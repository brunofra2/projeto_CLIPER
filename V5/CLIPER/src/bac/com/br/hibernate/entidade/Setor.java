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
@Table(name = "setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s"),
    @NamedQuery(name = "Setor.findById", query = "SELECT s FROM Setor s WHERE s.id = :id"),
    @NamedQuery(name = "Setor.findByNomeSetor", query = "SELECT s FROM Setor s WHERE s.nomeSetor = :nomeSetor"),
    @NamedQuery(name = "Setor.findByResponsavel", query = "SELECT s FROM Setor s WHERE s.responsavel = :responsavel"),
    @NamedQuery(name = "Setor.findByEmail", query = "SELECT s FROM Setor s WHERE s.email = :email")})
public class Setor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String responsavel;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 50)
    private String situacao;
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "nome_setor", nullable = false, length = 100)
    private String nomeSetor;
    
    @OneToMany(mappedBy = "setorId", fetch = FetchType.LAZY)
    private List<Funcao> funcaoList;
    @OneToMany(mappedBy = "setorId", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "idSetorId", fetch = FetchType.LAZY)
    private List<Descricao> descricaoList;
    @OneToMany(mappedBy = "idSetor", fetch = FetchType.LAZY)
    private List<Treinador> treinadorList;

    public Setor() {
    }

    public Setor(Long id) {
        this.id = id;
    }

    public Setor(Long id, String nomeSetor, String responsavel, String email) {
        this.id = id;
        this.nomeSetor = nomeSetor;
        this.responsavel = responsavel;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<Descricao> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<Descricao> descricaoList) {
        this.descricaoList = descricaoList;
    }

    @XmlTransient
    public List<Treinador> getTreinadorList() {
        return treinadorList;
    }

    public void setTreinadorList(List<Treinador> treinadorList) {
        this.treinadorList = treinadorList;
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
        if (!(object instanceof Setor)) {
            return false;
        }
        Setor other = (Setor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Setor[ id=" + id + " ]";
    }


    public Setor(Long id, String responsavel, String email) {
        this.id = id;
        this.responsavel = responsavel;
        this.email = email;
    }

   
   

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

   
    
}
