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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "descricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descricao.findAll", query = "SELECT d FROM Descricao d")
    , @NamedQuery(name = "Descricao.findById", query = "SELECT d FROM Descricao d WHERE d.id = :id")
    , @NamedQuery(name = "Descricao.findByArea", query = "SELECT d FROM Descricao d WHERE d.area = :area")
    , @NamedQuery(name = "Descricao.findByEscolaridadeDes", query = "SELECT d FROM Descricao d WHERE d.escolaridadeDes = :escolaridadeDes")
    , @NamedQuery(name = "Descricao.findByEscolaridadeMin", query = "SELECT d FROM Descricao d WHERE d.escolaridadeMin = :escolaridadeMin")
    , @NamedQuery(name = "Descricao.findByExperiencia", query = "SELECT d FROM Descricao d WHERE d.experiencia = :experiencia")
    , @NamedQuery(name = "Descricao.findByFaixaEtaria", query = "SELECT d FROM Descricao d WHERE d.faixaEtaria = :faixaEtaria")
    , @NamedQuery(name = "Descricao.findByHabilitacaoProfissional", query = "SELECT d FROM Descricao d WHERE d.habilitacaoProfissional = :habilitacaoProfissional")
    , @NamedQuery(name = "Descricao.findBySexo", query = "SELECT d FROM Descricao d WHERE d.sexo = :sexo")})
public class Descricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao_detalhada")
    private String descricaoDetalhada;
    @Basic(optional = false)
    @Column(name = "escolaridade_des")
    private String escolaridadeDes;
    @Basic(optional = false)
    @Column(name = "escolaridade_min")
    private String escolaridadeMin;
    @Basic(optional = false)
    @Column(name = "experiencia")
    private String experiencia;
    @Basic(optional = false)
    @Column(name = "faixa_etaria")
    private String faixaEtaria;
    @Basic(optional = false)
    @Lob
    @Column(name = "habilidade")
    private String habilidade;
    @Basic(optional = false)
    @Column(name = "habilitacao_profissional")
    private String habilitacaoProfissional;
    @Basic(optional = false)
    @Lob
    @Column(name = "lidera")
    private String lidera;
    @Basic(optional = false)
    @Lob
    @Column(name = "missao")
    private String missao;
    @Basic(optional = false)
    @Lob
    @Column(name = "responsabilidades")
    private String responsabilidades;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Lob
    @Column(name = "supervisao")
    private String supervisao;
    @Basic(optional = false)
    @Lob
    @Column(name = "treinamento_legais")
    private String treinamentoLegais;
    @Basic(optional = false)
    @Lob
    @Column(name = "integracao")
    private String integracao;
    @JoinTable(name = "descricao_documento", joinColumns = {
        @JoinColumn(name = "descricao_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_documento_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "idDescricao", fetch = FetchType.LAZY)
    private List<Historico> historicoList;
    @JoinColumn(name = "id_funcao_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idFuncaoId;
    @JoinColumn(name = "id_setor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor idSetorId;

    public Descricao() {
    }

    public Descricao(Long id) {
        this.id = id;
    }

    public Descricao(Long id, String area, String descricaoDetalhada, String escolaridadeDes, String escolaridadeMin, String experiencia, String faixaEtaria, String habilidade, String habilitacaoProfissional, String lidera, String missao, String responsabilidades, String sexo, String supervisao, String treinamentoLegais, String integracao) {
        this.id = id;
        this.area = area;
        this.descricaoDetalhada = descricaoDetalhada;
        this.escolaridadeDes = escolaridadeDes;
        this.escolaridadeMin = escolaridadeMin;
        this.experiencia = experiencia;
        this.faixaEtaria = faixaEtaria;
        this.habilidade = habilidade;
        this.habilitacaoProfissional = habilitacaoProfissional;
        this.lidera = lidera;
        this.missao = missao;
        this.responsabilidades = responsabilidades;
        this.sexo = sexo;
        this.supervisao = supervisao;
        this.treinamentoLegais = treinamentoLegais;
        this.integracao = integracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getEscolaridadeDes() {
        return escolaridadeDes;
    }

    public void setEscolaridadeDes(String escolaridadeDes) {
        this.escolaridadeDes = escolaridadeDes;
    }

    public String getEscolaridadeMin() {
        return escolaridadeMin;
    }

    public void setEscolaridadeMin(String escolaridadeMin) {
        this.escolaridadeMin = escolaridadeMin;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getHabilitacaoProfissional() {
        return habilitacaoProfissional;
    }

    public void setHabilitacaoProfissional(String habilitacaoProfissional) {
        this.habilitacaoProfissional = habilitacaoProfissional;
    }

    public String getLidera() {
        return lidera;
    }

    public void setLidera(String lidera) {
        this.lidera = lidera;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getResponsabilidades() {
        return responsabilidades;
    }

    public void setResponsabilidades(String responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSupervisao() {
        return supervisao;
    }

    public void setSupervisao(String supervisao) {
        this.supervisao = supervisao;
    }

    public String getTreinamentoLegais() {
        return treinamentoLegais;
    }

    public void setTreinamentoLegais(String treinamentoLegais) {
        this.treinamentoLegais = treinamentoLegais;
    }

    public String getIntegracao() {
        return integracao;
    }

    public void setIntegracao(String integracao) {
        this.integracao = integracao;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    public Funcao getIdFuncaoId() {
        return idFuncaoId;
    }

    public void setIdFuncaoId(Funcao idFuncaoId) {
        this.idFuncaoId = idFuncaoId;
    }

    public Setor getIdSetorId() {
        return idSetorId;
    }

    public void setIdSetorId(Setor idSetorId) {
        this.idSetorId = idSetorId;
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
        if (!(object instanceof Descricao)) {
            return false;
        }
        Descricao other = (Descricao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Descricao[ id=" + id + " ]";
    }
    
}
