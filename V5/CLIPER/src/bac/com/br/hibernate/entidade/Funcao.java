/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "funcao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcao.findAll", query = "SELECT f FROM Funcao f"),
    @NamedQuery(name = "Funcao.findById", query = "SELECT f FROM Funcao f WHERE f.id = :id"),
    @NamedQuery(name = "Funcao.findByFuncao", query = "SELECT f FROM Funcao f WHERE f.funcao = :funcao"),
    @NamedQuery(name = "Funcao.findByCondicao", query = "SELECT f FROM Funcao f WHERE f.condicao = :condicao")})
public class Funcao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String funcao;
    @Column(length = 100)
    private String condicao;
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id_domicilio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Domicilio domicilioId;
    @JoinColumn(name = "lotacao_id", referencedColumnName = "id_locacao")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotacao lotacaoId;
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor setorId;
    @OneToMany(mappedBy = "idCargo", fetch = FetchType.LAZY)
    private List<Atividades> atividadesList;
    @OneToMany(mappedBy = "idCargo", fetch = FetchType.LAZY)
    private List<Historico> historicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCargo", fetch = FetchType.LAZY)
    private List<ItemSeguranca> itemSegurancaList;
    @OneToMany(mappedBy = "idFuncaoId", fetch = FetchType.LAZY)
    private List<Descricao> descricaoList;
    @OneToMany(mappedBy = "funcaoId", fetch = FetchType.LAZY)
    private List<Colaborador> colaboradorList;
    @OneToMany(mappedBy = "idCargo", fetch = FetchType.LAZY)
    private List<Risco> riscoList;

    public Funcao() {
    }

    public Funcao(Long id) {
        this.id = id;
    }

    public Funcao(Long id, String funcao) {
        this.id = id;
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public Domicilio getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(Domicilio domicilioId) {
        this.domicilioId = domicilioId;
    }

    public Lotacao getLotacaoId() {
        return lotacaoId;
    }

    public void setLotacaoId(Lotacao lotacaoId) {
        this.lotacaoId = lotacaoId;
    }

    public Setor getSetorId() {
        return setorId;
    }

    public void setSetorId(Setor setorId) {
        this.setorId = setorId;
    }

    @XmlTransient
    public List<Atividades> getAtividadesList() {
        return atividadesList;
    }

    public void setAtividadesList(List<Atividades> atividadesList) {
        this.atividadesList = atividadesList;
    }

    @XmlTransient
    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    @XmlTransient
    public List<ItemSeguranca> getItemSegurancaList() {
        return itemSegurancaList;
    }

    public void setItemSegurancaList(List<ItemSeguranca> itemSegurancaList) {
        this.itemSegurancaList = itemSegurancaList;
    }

    @XmlTransient
    public List<Descricao> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<Descricao> descricaoList) {
        this.descricaoList = descricaoList;
    }

    @XmlTransient
    public List<Colaborador> getColaboradorList() {
        return colaboradorList;
    }

    public void setColaboradorList(List<Colaborador> colaboradorList) {
        this.colaboradorList = colaboradorList;
    }

    @XmlTransient
    public List<Risco> getRiscoList() {
        return riscoList;
    }

    public void setRiscoList(List<Risco> riscoList) {
        this.riscoList = riscoList;
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
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Funcao[ id=" + id + " ]";
    }
    
}
