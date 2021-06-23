/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;

import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.modulo.treinamento.Multipla_impressao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.relatorios.Relatorio_detalhado;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class teste {

    public static void main(String args[]) {

                        try {
                            
                            // DESCRIÇÃO DE CARGO
                            Rel_desc_car rel = new Rel_desc_car();
                            rel.setDocs(new Daos_Nativos().impressao_documentos(180,"IT-%"));
                            rel.setIds(new DescricaoDao().comparar_setores(Long.valueOf(180),"IT-%"));
                            //rel.setNums(new Daos_Nativos().setor(19));
                            String resultado = Utils.concatenar(rel.getIds(), rel.getDocs());
                            System.out.println("resultado"+resultado);
                            
                            
                            
                            // ATA DE TREINAMENTO
                            Rel_desc_car rel2 = new Rel_desc_car();
                            rel.setDocs( new Daos_Nativos().setor(1561));
                            rel.setIds(new DescricaoDao().documentos(1561));
                            String resultado2 = Utils.concatenar(rel.getIds(), rel.getDocs());
                            System.out.println("resultado"+resultado2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
       // transmissao tran = new transmissao();
        
        //tran.transferir();
        
       // Utils u = new Utils();

       //u.relatorio_estatistico("2019");
       
        /* 
        Relatorio_detalhado det;
        det = new Relatorio_detalhado(Utils.Sistema_operacional(),"ALMOXARIFADO");
        det.Calculos();
        det.comparar_listas("VENDA");
        
        Double a1 = 3.80;
        Double a2 = Math.floor(a1)+0.60;
        Double res = a1 - a2;
        
                Locale lol = new Locale("pt","BR");
                NumberFormat format = NumberFormat.getNumberInstance(lol);
                format.setMinimumFractionDigits(2);
        System.out.println(format.format(res));
        
         try {
            List<Informacoes_horas> lista = new Daos_Nativos().calcular_hora("","'2017'");
            
                String h1 = "";
                String h2 = "";
                Double soma = 0.0;
            for (Informacoes_horas treinamento : lista) {
                if(treinamento.getH1().contains("h")){
                    h1 = treinamento.getH1().replace("h", ".");
                }else{
                    if(treinamento.getH2().contains(":")){
                        h1 = treinamento.getH2().replace(":", ".");
                    }
                }
                if(treinamento.getH2().contains("h")){
                     h2 = treinamento.getH2().replace("h", ".");
                }else{
                    if(treinamento.getH2().contains(":")){
                     h2 = treinamento.getH2().replace(":", ".");
                    }
                }
                Double hora_inicio;
                Double hora_fim;
                Double calcular;
                Double sobra_minutos;
                Double recalculo;
                hora_inicio = Double.parseDouble(h1);
                hora_fim = Double.parseDouble(h2);
                sobra_minutos = hora_fim - Math.floor(hora_fim);
                calcular = Math.floor(hora_fim)-0.40 - hora_inicio + sobra_minutos;
                
                if((calcular+0.40) >= (Math.floor(calcular)+1)){
                sobra_minutos = calcular - Math.floor(calcular);
                recalculo = Math.floor(calcular) + 1 + (sobra_minutos -0.60) ;
                }else{
                   recalculo = calcular; 
                }
                    String a = format.format(recalculo);
                if(a.equals("0,60")){
                    recalculo = 1.00;
                }
                System.out.println(format.format(hora_fim)+"-"+format.format(hora_inicio)+"="+format.format(recalculo));
                soma = soma + recalculo;
            }
             System.out.println(format.format(soma));
            System.out.println(lista.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        boolean verifica = false;
        List<String> nomes = new ArrayList<>();
        List<get_set_Grafico> Documentos_treinados = new Daos_Nativos().pegar_realizados();
        List<get_set_Grafico> Documentos_obrigatorios = new Daos_Nativos().pegar_obrigatorios();
            for (get_set_Grafico grafico : Documentos_treinados) {
                for (get_set_Grafico select : Documentos_obrigatorios) {
                    if(grafico.getTitulo_doc().equals(select.getTitulo_doc())){
                        verifica = true;
                    }else{
                        verifica = false;
                    }
                    if(verifica == true){
                        for (String nome : nomes) {
                            if (nome.equals(grafico.getNome_col())) {
                                
                            }else{
                                nomes.add(grafico.getNome_col());
                            }
                        }
                        nomes.size();
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        List<String> lista_nome = new ArrayList<>();
        List<BigInteger> lista_id = new ArrayList<>();
        List<String> lista_nome_treinados = new ArrayList<>();
        List<BigInteger> lista_id_treinados = new ArrayList<>();
         */

 /*
        try {
            lista_nome = new TreinamentoDao().Quantidade_colaborador_nome();
            lista_id = new TreinamentoDao().Quantidade_colaborador_id();
            lista_nome_treinados = new TreinamentoDao().quantidade_treinados_nome();
            lista_id_treinados = new TreinamentoDao().quantidade_treinados_id();
            for (String string : lista_nome) {
                System.out.println(string.toString()); 
            }
            for (BigInteger bigInteger : lista_id) {
                System.out.println(bigInteger.toString());
            }
            for (String string : lista_nome_treinados) {
                System.out.println(string);
            }
            for (BigInteger id_treinado : lista_id_treinados) {
                System.out.println(id_treinado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
