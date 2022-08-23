package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "treinador")
public class Treinador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String formacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "email_supervisor", nullable = false, length = 50)
    private String emailSupervisor;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String supervisor;
    @OneToMany(mappedBy = "treinadorId", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa empresaId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;
    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor idSetor;

    public Treinador() {
    }

    public Treinador(Long id) {
        this.id = id;
    }

    public Treinador(Long id, String formacao, String nome, String email, String emailSupervisor, String supervisor) {
        this.id = id;
        this.formacao = formacao;
        this.nome = nome;
        this.email = email;
        this.emailSupervisor = emailSupervisor;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailSupervisor() {
        return emailSupervisor;
    }

    public void setEmailSupervisor(String emailSupervisor) {
        this.emailSupervisor = emailSupervisor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<Treinamento> getTreinamentoList() {
        return treinamentoList;
    }

    public void setTreinamentoList(List<Treinamento> treinamentoList) {
        this.treinamentoList = treinamentoList;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Setor getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Setor idSetor) {
        this.idSetor = idSetor;
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
        if (!(object instanceof Treinador)) {
            return false;
        }
        Treinador other = (Treinador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Treinador[ id=" + id + " ]";
    }
    
}
