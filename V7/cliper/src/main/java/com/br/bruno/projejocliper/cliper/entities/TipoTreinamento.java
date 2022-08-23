package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author bruno
 */
@Entity
@Table(name = "tipo_treinamento")
public class TipoTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int periodo;
    @Basic(optional = false)
    @Column(name = "tipo_treinamento", nullable = false, length = 255)
    private String tipoTreinamento;
    @OneToMany(mappedBy = "tipoTreinamentoId", fetch = FetchType.LAZY)
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "tipoTreinamentoId", fetch = FetchType.LAZY)
    private List<Treinamento> treinamentoList;

    public TipoTreinamento() {
    }

    public TipoTreinamento(Long id) {
        this.id = id;
    }

    public TipoTreinamento(Long id, int periodo, String tipoTreinamento) {
        this.id = id;
        this.periodo = periodo;
        this.tipoTreinamento = tipoTreinamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getTipoTreinamento() {
        return tipoTreinamento;
    }

    public void setTipoTreinamento(String tipoTreinamento) {
        this.tipoTreinamento = tipoTreinamento;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<Treinamento> getTreinamentoList() {
        return treinamentoList;
    }

    public void setTreinamentoList(List<Treinamento> treinamentoList) {
        this.treinamentoList = treinamentoList;
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
        if (!(object instanceof TipoTreinamento)) {
            return false;
        }
        TipoTreinamento other = (TipoTreinamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.TipoTreinamento[ id=" + id + " ]";
    }
    
}
