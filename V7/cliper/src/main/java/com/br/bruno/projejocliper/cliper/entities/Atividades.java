package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "atividades")
public class Atividades {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(length = 3000)
    private String descricao;
    @Column(length = 50)
    private String complemento;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcao idCargo;

    public Atividades() {
    }

    public Atividades(Long id, String descricao, String complemento, Funcao idCargo) {
        this.id = id;
        this.descricao = descricao;
        this.complemento = complemento;
        this.idCargo = idCargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Funcao getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Funcao idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividades that = (Atividades) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(complemento, that.complemento) && Objects.equals(idCargo, that.idCargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, complemento, idCargo);
    }
}