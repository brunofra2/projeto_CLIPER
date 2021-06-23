/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.relatorios;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author bruno
 */
public class Relatorio_detalhado {
    
    // VARIAVEIS PARA O METODO CALCULOS
    List<BigInteger> lista_documento = new ArrayList<BigInteger>();
    List<BigInteger> lista_treinados = new ArrayList<BigInteger>();
    List<BigInteger> lista_grafico = new ArrayList<BigInteger>();
    List<Colaborador> lista_colaboradores = new ArrayList<Colaborador>();
    Double total;
    Double treinados;
    Double pendentes;
    String colaboradores;
    
    // VARIAVEIS PARA AO METODO COMPARAR_LISTAS
    
    List<Setor> lista_setor = new ArrayList<>();
    List<String> lista_obrigatorios = new ArrayList<>();
    List<String> lista_realizados = new ArrayList<>();
    List<BigInteger> todos = new ArrayList<>();
    List<String> combinacoes = new ArrayList<>();
    String rel = "";
    Integer get = 0;
    Integer get2 =0;
    Integer get3 = 0;
    Long id;
    
     public Relatorio_detalhado(String imagem,String setor,String ano){
         try {
             this.Calculos(ano);
             this.comparar_listas(setor,ano);
            Connection conexao = Utils.open();
            HashMap parametro = new HashMap();
            parametro.put("TREINADO", treinados);
            parametro.put("RESULTADO",pendentes);
            parametro.put("colaboradores",300);
            parametro.put("documentos",total);
            parametro.put("get2",get2);
            parametro.put("get3",get3);
            parametro.put("id_setor",id);
            if(imagem.contains("home")){
                parametro.put("imagem", imagem+"/treinamento/mineiro.png");
            }else{
                parametro.put("imagem", imagem+"\\mineiro.png");
            }
            InputStream caminhorelatorio = getClass().getClassLoader().getResourceAsStream("bac/com/br/hibernate/relatorios/Relatorio_detalhado.jasper");
                JasperPrint printreport;
               printreport = JasperFillManager.fillReport(caminhorelatorio, parametro, conexao);
            
            /* 
             JasperPrint printreport = JasperFillManager.fillReport(caminhorelatorio,parametro, conexao);
             String nomerelatorio
             = System.getProperty("java.io.tmpdir") + "/Relatorio" + Utils.getNome() + ".pdf";
             JasperExportManager.exportReportToPdfFile(printreport, nomerelatorio);
             Runtime.getRuntime().exec("cmd /c start " + nomerelatorio);
             File file = new File(nomerelatorio);
             file.deleteOnExit();
             */
            JRViewer view = new JRViewer(printreport);
            JDialog dialog = new javax.swing.JDialog(new javax.swing.JFrame(), "relatorio", true);
            dialog.setSize(500, 500);
            Utils.maximizar(dialog);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.add(view, BorderLayout.CENTER);
            dialog.setVisible(true);
            view.show();

        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "Erro ao Gerar o Relatorio" + Utils.getErro(e));
        }
     }
     public void Calculos(String ano) {
        try {
            // GRAFICO PRINCIPAL GERAL DA EMPRESA
            lista_documento = new DocumentoDao().todos();
            lista_treinados = new DocumentoDao().grafico(ano);
            total = Double.valueOf(lista_documento.size());
            treinados = Double.valueOf(lista_treinados.size());
            pendentes = total - treinados;
            
            // INFORMAÇÕES GERAIS SOBRE A EMPRESA 
            lista_colaboradores = new ColaboradorDao().getlista("ATIVOS");
            colaboradores = String.valueOf(lista_colaboradores.size());
            System.out.println("treinados "+treinados);
            System.out.println("pendentes "+pendentes);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro ao calcular grafico \n"+e.getMessage());
        }
     }
     public void comparar_listas(String setor,String ano){
         lista_setor = new SetorDao().getlista(setor);
        
         for (Setor set : lista_setor) {
             id = set.getId();
             System.out.println("setor: "+set.getNomeSetor());
             lista_obrigatorios = new DocumentoDao().concat_obrigatorios(set.getId());
             lista_realizados = new DocumentoDao().concat_realizados(set.getId(),ano);
             System.out.println("lista1 "+lista_obrigatorios.size());
             System.out.println("lista2 "+lista_realizados.size());
                for (String obr : lista_obrigatorios) {
                    for (String real : lista_realizados) {
                        descomprimir(obr, real);
                    }
                }
                System.out.println("*"+rel.length());
             if (rel.length() > 0) {
                 rel = rel.substring (0, rel.length() - 1);
                 System.out.println("rel "+rel);
                 combinacoes = new DocumentoDao().listar_documentos(rel);
                 todos = new DocumentoDao().todos(set.getId());
                 get = todos.size();
                 get2 = combinacoes.size(); 
                 get3 = get-get2;
                 System.out.println("///////////////////////");
                         System.out.println(combinacoes.size());
                         System.out.println(get+"-"+get2+"="+get3);
             }
             rel = "";
             //get = 0;
             //get2 = 0;
             //get3 = 0;
         }
     }
     public void descomprimir(String a,String b){
        String c[] = a.split(",");
        String d[] = b.split(",");
        for(String obri : c){
            for(String reali : d){
                if(reali.equals(obri)){
                    
                    if(rel.contains(obri)){
                        
                    }else{
                    rel+=obri+",";
                    }
                }
            }
        }
        
     }
     
}
