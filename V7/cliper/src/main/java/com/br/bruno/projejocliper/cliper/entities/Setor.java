package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "setor")
public class Setor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false, name = "id_setor")
    private Long idSetor;
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
    
    @OneToMany(mappedBy = "idSetor", fetch = FetchType.LAZY)
    private List<Funcao> funcaoList;
    @OneToMany(mappedBy = "setorId", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "idSetorId", fetch = FetchType.LAZY)
    private List<Descricao> descricaoList;
    @OneToMany(mappedBy = "idSetor", fetch = FetchType.LAZY)
    private List<Treinador> treinadorList;

    public Setor() {
    }


    public Setor(Long id, String nomeSetor, String responsavel, String email) {
        this.idSetor = id;
        this.nomeSetor = nomeSetor;
        this.responsavel = responsavel;
        this.email = email;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
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

    public List<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<Descricao> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<Descricao> descricaoList) {
        this.descricaoList = descricaoList;
    }

    public List<Treinador> getTreinadorList() {
        return treinadorList;
    }

    public void setTreinadorList(List<Treinador> treinadorList) {
        this.treinadorList = treinadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetor != null ? idSetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setor)) {
            return false;
        }
        Setor other = (Setor) object;
        if ((this.idSetor == null && other.idSetor != null) || (this.idSetor != null && !this.idSetor.equals(other.idSetor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Setor[ id=" + idSetor + " ]";
    }


    public Setor(Long id, String responsavel, String email) {
        this.idSetor = id;
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
