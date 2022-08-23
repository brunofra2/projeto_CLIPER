package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Objects;

public class TreinadorDto implements Serializable {
    private final Long id;
    private final String formacao;
    private final String nome;
    private final String email;
    private final String emailSupervisor;
    private final String supervisor;

    public TreinadorDto(Long id, String formacao, String nome, String email, String emailSupervisor, String supervisor) {
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

    public String getFormacao() {
        return formacao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailSupervisor() {
        return emailSupervisor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreinadorDto entity = (TreinadorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.formacao, entity.formacao) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.emailSupervisor, entity.emailSupervisor) &&
                Objects.equals(this.supervisor, entity.supervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formacao, nome, email, emailSupervisor, supervisor);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "formacao = " + formacao + ", " +
                "nome = " + nome + ", " +
                "email = " + email + ", " +
                "emailSupervisor = " + emailSupervisor + ", " +
                "supervisor = " + supervisor + ")";
    }
}
