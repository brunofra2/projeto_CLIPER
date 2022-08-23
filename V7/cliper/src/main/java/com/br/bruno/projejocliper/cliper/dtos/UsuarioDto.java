package com.br.bruno.projejocliper.cliper.dtos;

import com.br.bruno.projejocliper.cliper.dtos.BackupDto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UsuarioDto implements Serializable {
    private  Long id;
    private String codigo;
    private String senha;
    private String tipo;
    private List<BackupDto> backupList;
    private List<TreinadorDto> treinadorList;

    public UsuarioDto() {
    }

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

    public List<BackupDto> getBackupList() {
        return backupList;
    }

    public void setBackupList(List<BackupDto> backupList) {
        this.backupList = backupList;
    }

    public List<TreinadorDto> getTreinadorList() {
        return treinadorList;
    }

    public void setTreinadorList(List<TreinadorDto> treinadorList) {
        this.treinadorList = treinadorList;
    }
}
