/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;

import java.util.Date;

/**
 *
 * @author bruno
 */
public class Get_set_tabela_funcoes {
    
        private Date inicio;
        private Date fim;
        private Long id_descricao;
        private String funcao;
        private Long id_funcao;
        private Long id;

    public Long getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(Long id_funcao) {
        this.id_funcao = id_funcao;
    }

        
    
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

        
    
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Long getId_descricao() {
        return id_descricao;
    }

    public void setId_descricao(Long id_descricao) {
        this.id_descricao = id_descricao;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

        
        
    
}
