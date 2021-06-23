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
@Table(name = "backup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Backup.findAll", query = "SELECT b FROM Backup b"),
    @NamedQuery(name = "Backup.findById", query = "SELECT b FROM Backup b WHERE b.id = :id"),
    @NamedQuery(name = "Backup.findByCaminho", query = "SELECT b FROM Backup b WHERE b.caminho = :caminho"),
    @NamedQuery(name = "Backup.findByDataBackup", query = "SELECT b FROM Backup b WHERE b.dataBackup = :dataBackup")})
public class Backup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 500)
    private String caminho;
    @Column(name = "data_backup")
    @Temporal(TemporalType.DATE)
    private Date dataBackup;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuarioId;

    public Backup() {
    }

    public Backup(Long id) {
        this.id = id;
    }

    public Backup(Long id, String caminho) {
        this.id = id;
        this.caminho = caminho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Date getDataBackup() {
        return dataBackup;
    }

    public void setDataBackup(Date dataBackup) {
        this.dataBackup = dataBackup;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof Backup)) {
            return false;
        }
        Backup other = (Backup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bac.com.br.hibernate.entidade.Backup[ id=" + id + " ]";
    }
    
}
