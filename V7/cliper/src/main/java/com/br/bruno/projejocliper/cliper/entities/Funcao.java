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

    @JoinColumn(name = "id_setor", referencedColumnName = "id_setor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Setor idSetor;

    @JoinColumn(name = "id_lotacao", referencedColumnName = "id_lotacao", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lotacao idLotacao;

    @JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Domicilio idDomicilio;

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
