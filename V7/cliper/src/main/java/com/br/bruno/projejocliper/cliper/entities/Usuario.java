package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author bruno
 */
@Entity
@Table(name = "usuario")
public class Usuario implements  Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String codigo;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String senha;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String tipo;
    @OneToMany(mappedBy = "usuarioId", fetch = FetchType.LAZY)
    private List<Backup> backupList;
    @OneToMany(mappedBy = "usuarioId", fetch = FetchType.LAZY)
    private List<Treinador> treinadorList;

    public Usuario() {
    }

    public Usuario(Long id, String codigo, String senha, String tipo, List<Backup> backupList, List<Treinador> treinadorList) {
        this.id = id;
        this.codigo = codigo;
        this.senha = senha;
        this.tipo = tipo;
        this.backupList = backupList;
        this.treinadorList = treinadorList;
    }

    public Usuario(Long id, String codigo, String senha, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Backup> getBackupList() {
        return backupList;
    }

    public void setBackupList(List<Backup> backupList) {
        this.backupList = backupList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Usuario[ id=" + id + " ]";
    }
    
}
