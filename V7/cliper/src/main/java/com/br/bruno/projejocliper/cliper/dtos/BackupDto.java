package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BackupDto implements Serializable {
    private final Long id;
    private final String caminho;
    private final Date dataBackup;
    private final UsuarioDto usuarioId;

    public BackupDto(Long id, String caminho, Date dataBackup, UsuarioDto usuarioId) {
        this.id = id;
        this.caminho = caminho;
        this.dataBackup = dataBackup;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public String getCaminho() {
        return caminho;
    }

    public Date getDataBackup() {
        return dataBackup;
    }

    public UsuarioDto getUsuarioId() {
        return usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackupDto entity = (BackupDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.caminho, entity.caminho) &&
                Objects.equals(this.dataBackup, entity.dataBackup) &&
                Objects.equals(this.usuarioId, entity.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caminho, dataBackup, usuarioId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "caminho = " + caminho + ", " +
                "dataBackup = " + dataBackup + ", " +
                "usuarioId = " + usuarioId + ")";
    }
}
