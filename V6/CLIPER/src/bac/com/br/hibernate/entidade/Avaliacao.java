/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "avaliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a")
    , @NamedQuery(name = "Avaliacao.findByIdAvaliacao", query = "SELECT a FROM Avaliacao a WHERE a.idAvaliacao = :idAvaliacao")
    , @NamedQuery(name = "Avaliacao.findByDataExames", query = "SELECT a FROM Avaliacao a WHERE a.dataExames = :dataExames")
    , @NamedQuery(name = "Avaliacao.findByDataTreinamento", query = "SELECT a FROM Avaliacao a WHERE a.dataTreinamento = :dataTreinamento")
    , @NamedQuery(name = "Avaliacao.findByDataAvaliacao", query = "SELECT a FROM Avaliacao a WHERE a.dataAvaliacao = :dataAvaliacao")
    , @NamedQuery(name = "Avaliacao.findByFimExperiencia", query = "SELECT a FROM Avaliacao a WHERE a.fimExperiencia = :fimExperiencia")
    , @NamedQuery(name = "Avaliacao.findByEstatusAvaliacao", query = "SELECT a FROM Avaliacao a WHERE a.estatusAvaliacao = :estatusAvaliacao")
    , @NamedQuery(name = "Avaliacao.findByTempoAtual", query = "SELECT a FROM Avaliacao a WHERE a.tempoAtual = :tempoAtual")
    , @NamedQuery(name = "Avaliacao.findBySalarioAtual", query = "SELECT a FROM Avaliacao a WHERE a.salarioAtual = :salarioAtual")
    , @NamedQuery(name = "Avaliacao.findBySalarioProposto", query = "SELECT a FROM Avaliacao a WHERE a.salarioProposto = :salarioProposto")
    , @NamedQuery(name = "Avaliacao.findByExames", query = "SELECT a FROM Avaliacao a WHERE a.exames = :exames")
    , @NamedQuery(name = "Avaliacao.findByCondicao", query = "SELECT a FROM Avaliacao a WHERE a.condicao = :condicao")
    , @NamedQuery(name = "Avaliacao.findByTreinamentos", query = "SELECT a FROM Avaliacao a WHERE a.treinamentos = :treinamentos")
    , @NamedQuery(name = "Avaliacao.findByDataSup", query = "SELECT a FROM Avaliacao a WHERE a.dataSup = :dataSup")
    , @NamedQuery(name = "Avaliacao.findBySituacao", query = "SELECT a FROM Avaliacao a WHERE a.situacao = :situacao")
    , @NamedQuery(name = "Avaliacao.findByParecerSup", query = "SELECT a FROM Avaliacao a WHERE a.parecerSup = :parecerSup")
    , @NamedQuery(name = "Avaliacao.findByDataGen", query = "SELECT a FROM Avaliacao a WHERE a.dataGen = :dataGen")
    , @NamedQuery(name = "Avaliacao.findByParecerGen", query = "SELECT a FROM Avaliacao a WHERE a.parecerGen = :parecerGen")
    , @NamedQuery(name = "Avaliacao.findByDataRh", query = "SELECT a FROM Avaliacao a WHERE a.dataRh = :dataRh")
    , @NamedQuery(name = "Avaliacao.findByParecerRh", query = "SELECT a FROM Avaliacao a WHERE a.parecerRh = :parecerRh")})
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_avaliacao")
    private Long idAvaliacao;
    @Column(name = "data_exames")
    @Temporal(TemporalType.DATE)
    private Date dataExames;
    @Column(name = "data_treinamento")
    @Temporal(TemporalType.DATE)
    private Date dataTreinamento;
    @Column(name = "data_avaliacao")
    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;
    @Column(name = "fim_experiencia")
    @Temporal(TemporalType.DATE)
    private Date fimExperiencia;
    @Column(name = "estatus_avaliacao")
    private String estatusAvaliacao;
    @Column(name = "tempo_atual")
    private String tempoAtual;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_atual")
    private Double salarioAtual;
    @Column(name = "salario_proposto")
    private Double salarioProposto;
    @Column(name = "exames")
    private String exames;
    @Column(name = "condicao")
    private String condicao;
    @Column(name = "treinamentos")
    private String treinamentos;
    @Column(name = "data_sup")
    @Temporal(TemporalType.DATE)
    private Date dataSup;
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "parecer_sup")
    private String parecerSup;
    @Column(name = "data_gen")
    @Temporal(TemporalType.DATE)
    private Date dataGen;
    @Column(name = "parecer_gen")
    private String parecerGen;
    @Column(name = "data_rh")
    @Temporal(TemporalType.DATE)
    private Date dataRh;
    @Column(name = "parecer_rh")
    private String parecerRh;
    @JoinColumn(name = "id_colaborador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Colaborador idColaborador;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idCargo;
    @JoinColumn(name = "id_cargo_atual", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idCargoAtual;

    public Avaliacao() {
    }

    public Avaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Date getDataExames() {
        return dataExames;
    }

    public void setDataExames(Date dataExames) {
        this.dataExames = dataExames;
    }

    public Date getDataTreinamento() {
        return dataTreinamento;
    }

    public void setDataTreinamento(Date dataTreinamento) {
        this.dataTreinamento = dataTreinamento;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getFimExperiencia() {
        return fimExperiencia;
    }

    public void setFimExperiencia(Date fimExperiencia) {
        this.fimExperiencia = fimExperiencia;
    }

    public String getEstatusAvaliacao() {
        return estatusAvaliacao;
    }

    public void setEstatusAvaliacao(String estatusAvaliacao) {
        this.estatusAvaliacao = estatusAvaliacao;
    }

    public String getTempoAtual() {
        return tempoAtual;
    }

    public void setTempoAtual(String tempoAtual) {
        this.tempoAtual = tempoAtual;
    }

    public Double getSalarioAtual() {
        return salarioAtual;
    }

    public void setSalarioAtual(Double salarioAtual) {
        this.salarioAtual = salarioAtual;
    }

    public Double getSalarioProposto() {
        return salarioProposto;
    }

    public void setSalarioProposto(Double salarioProposto) {
        this.salarioProposto = salarioProposto;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(String treinamentos) {
        this.treinamentos = treinamentos;
    }

    public Date getDataSup() {
        return dataSup;
    }

    public void setDataSup(Date dataSup) {
        this.dataSup = dataSup;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getParecerSup() {
        return parecerSup;
    }

    public void setParecerSup(String parecerSup) {
        this.parecerSup = parecerSup;
    }

    public Date getDataGen() {
        return dataGen;
    }

    public void setDataGen(Date dataGen) {
        this.dataGen = dataGen;
    }

    public String getParecerGen() {
        return parecerGen;
    }

    public void setParecerGen(String parecerGen) {
        this.parecerGen = parecerGen;
    }

    public Date getDataRh() {
        return dataRh;
    }

    public void setDataRh(Date dataRh) {
        this.dataRh = dataRh;
    }

    public String getParecerRh() {
        return parecerRh;
    }

    public void setParecerRh(String parecerRh) {
        this.parecerRh = parecerRh;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Funcao getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Funcao idCargo) {
        this.idCargo = idCargo;
    }

    public Funcao getIdCargoAtual() {
        return idCargoAtual;
    }

    public void setIdCargoAtual(Funcao idCargoAtual) {
        this.idCargoAtual = idCargoAtual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvaliacao != null ? idAvaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idAvaliacao == null && other.idAvaliacao != null) || (this.idAvaliacao != null && !this.idAvaliacao.equals(other.idAvaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Avaliacao[ idAvaliacao=" + idAvaliacao + " ]";
    }
    
}
