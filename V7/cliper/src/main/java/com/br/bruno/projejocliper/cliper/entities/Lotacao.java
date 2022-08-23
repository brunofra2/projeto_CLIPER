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
    @Column(name = "id_lotacao", nullable = false)
    private Integer idLotacao;
    @Column(length = 50)
    private String titulo;
    @OneToMany(mappedBy = "idLotacao", fetch = FetchType.LAZY)
    private List<Funcao> funcaoList;
    @JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Domicilio idDomicilio;

    public Lotacao() {
    }


    public Integer getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(Integer idLotacao) {
        this.idLotacao = idLotacao;
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
        hash += (idLotacao != null ? idLotacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotacao)) {
            return false;
        }
        Lotacao other = (Lotacao) object;
        if ((this.idLotacao == null && other.idLotacao != null) || (this.idLotacao != null && !this.idLotacao.equals(other.idLotacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Lotacao[ idLocacao=" + idLotacao + " ]";
    }
    
}
