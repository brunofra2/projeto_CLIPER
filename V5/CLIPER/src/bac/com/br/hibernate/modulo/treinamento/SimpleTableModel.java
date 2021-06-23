/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.entidade.Historico;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author bruno
 */
public class SimpleTableModel extends AbstractTableModel{
   // Esta coleção representa os dados que estão sendo
    // exibidos na tabela.
    private List<Historico> alunos = new 
        ArrayList<>();
    // Estas variáveis representam as minhas colunas a serem exibidas.
    private static final int COLUNANOME = 0;
    private static final int COLUNANOTA = 1;
    private static final int COLUNAPASSOU = 2;
    private static final int colunachek = 3;
    
    
    
    // Você recebe, no construtor, a lista de alunos a ser exibida.
    public SimpleTableModel(List<Historico> alunos) {
        this.alunos = alunos;
    }

    // Retorna o número de linhas que a tabela tem.
    // O número de linhas é o tamanho da coleção, visto
    // que cada item da coleção é uma linha da tabela.
    public int getRowCount() {
            return alunos.size();
    }

    // Retorna o número de colunas que você quer exibir.
    public int getColumnCount() {
        return 4;
    }

    // Retorna o valor específico de uma célula, através
    // da linha e coluna que são recebidas por parâmetro.
    public Object getValueAt(int rowIndex, int columnIndex) {

        Historico aluno = this.alunos.get(rowIndex);

        if (columnIndex == COLUNANOME) return aluno.getIdCargo().getFuncao();
        if (columnIndex == COLUNANOTA) return aluno.getPeriodoInicio();
        if (columnIndex == COLUNAPASSOU) return aluno.getPeriodoFim();
        //if (columnIndex == colunachek) return aluno.getSelecao();
        
        return "";
    }

    // Retorna o nome da coluna através do índice recebido
    // por parâmetro.
    @Override public String getColumnName(int column) {

        if (column == COLUNANOME) return "Nome do aluno";
        if (column == COLUNANOTA) return "Nota no semestre";
        if (column == COLUNAPASSOU) return "Passou?";
        if (column == colunachek) return "checar";
        return "";
    }

    // A mágica funciona aqui.
    // Você deve definir qual a classe da informação a
    // ser exibida em uma determinada coluna. No caso,
    // devemos passar a classe Boolean para a coluna onde
    // nossa Checkbox irá aparecer.
    @Override public Class<?> getColumnClass(int columnIndex) {       
        if (columnIndex == colunachek) 
            return Boolean.class;
        else 
            return String.class;
    }


    // Você pode criar um método para recuperar quais os alunos que foram selecionados, como você necessita.
    public List<Tabela_historico> recuperarAlunosSelecionados() {
        List<Tabela_historico> alunosSelecionados = new ArrayList<>();

        /*for (Historico aluno : alunos) {
            if (aluno.getSelecao()) {
                alunosSelecionados.add(aluno);
            }
        }
        */
        return alunosSelecionados;
    }

}
