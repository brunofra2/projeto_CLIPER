package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 18)
    private String cnpj;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nome;
    @OneToMany(mappedBy = "empresaId", fetch = FetchType.LAZY)
    private List<Treinador> treinadorList;

    public Empresa() {
    }

    public Empresa(Long id) {
        this.id = id;
    }

    public Empresa(Long id, String cnpj, String nome) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Empresa[ id=" + id + " ]";
    }
    
}
