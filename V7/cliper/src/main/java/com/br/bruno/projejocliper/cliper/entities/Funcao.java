package com.br.bruno.projejocliper.cliper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "funcao")
public class Funcao {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String funcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
