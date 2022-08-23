package com.br.bruno.projejocliper.cliper.dtos;

import com.br.bruno.projejocliper.cliper.dtos.BackupDto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UsuarioDto implements Serializable {
    private final Long id;
    private final String codigo;
    private final String senha;
    private final String tipo;
    private final List<BackupDto> backupList;
    private final List<TreinadorDto> treinadorList;


    public UsuarioDto(Long id, String codigo, String senha, String tipo, List<BackupDto> backupList, List<TreinadorDto> treinadorList) {
        this.id = id;
        this.codigo = codigo;
        this.senha = senha;
        this.tipo = tipo;
        this.backupList = backupList;
        this.treinadorList = treinadorList;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public List<BackupDto> getBackupList() {
        return backupList;
    }

    public List<TreinadorDto> getTreinadorList() {
        return treinadorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.codigo, entity.codigo) &&
                Objects.equals(this.senha, entity.senha) &&
                Objects.equals(this.tipo, entity.tipo) &&
                Objects.equals(this.backupList, entity.backupList) &&
                Objects.equals(this.treinadorList, entity.treinadorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, senha, tipo, backupList, treinadorList);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "codigo = " + codigo + ", " +
                "senha = " + senha + ", " +
                "tipo = " + tipo + ", " +
                "backupList = " + backupList + ", " +
                "treinadorList = " + treinadorList + ")";
    }
}
