package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author bruno
 */
@Entity
@Table(name = "historico")
public class Historico{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico", nullable = false)
    private Long idHistorico;
    @Column(name = "periodo_inicio")
    @Temporal(TemporalType.DATE)
    private Date periodoInicio;
    @Column(name = "periodo_fim")
    @Temporal(TemporalType.DATE)
    private Date periodoFim;
    @JoinColumn(name = "id_colaborador", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Colaborador idColaborador;
    @JoinColumn(name = "id_descricao", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Descricao idDescricao;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idCargo;

    public Historico() {
    }

    public Historico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Date getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(Date periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public Date getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(Date periodoFim) {
        this.periodoFim = periodoFim;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Descricao getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(Descricao idDescricao) {
        this.idDescricao = idDescricao;
    }

    public Funcao getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Funcao idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorico != null ? idHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historico)) {
            return false;
        }
        Historico other = (Historico) object;
        if ((this.idHistorico == null && other.idHistorico != null) || (this.idHistorico != null && !this.idHistorico.equals(other.idHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Historico[ idHistorico=" + idHistorico + " ]";
    }
    
}
