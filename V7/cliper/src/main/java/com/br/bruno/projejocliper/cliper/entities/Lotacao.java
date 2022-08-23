package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author bruno
 */
@Entity
@Table(name = "lotacao")
public class Lotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_locacao", nullable = false)
    private Integer idLocacao;
    @Column(length = 50)
    private String titulo;
    @OneToMany(mappedBy = "lotacaoId", fetch = FetchType.LAZY)
    private List<Funcao> funcaoList;
    @JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Domicilio idDomicilio;

    public Lotacao() {
    }

    public Lotacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    public Domicilio getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Domicilio idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocacao != null ? idLocacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotacao)) {
            return false;
        }
        Lotacao other = (Lotacao) object;
        if ((this.idLocacao == null && other.idLocacao != null) || (this.idLocacao != null && !this.idLocacao.equals(other.idLocacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Lotacao[ idLocacao=" + idLocacao + " ]";
    }
    
}
