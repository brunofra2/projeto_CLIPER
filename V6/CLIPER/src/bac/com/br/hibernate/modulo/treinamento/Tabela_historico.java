/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import java.util.Date;

/**
 *
 * @author bruno
 */
public class Tabela_historico {
    private String cargo;
    private String data1;
    private String data2;
    private Boolean selecao;

    public String getCargo() {
        return cargo;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getSelecao() {
        return selecao;
    }

    public void setSelecao(Boolean selecao) {
        this.selecao = selecao;
    }
    
}
