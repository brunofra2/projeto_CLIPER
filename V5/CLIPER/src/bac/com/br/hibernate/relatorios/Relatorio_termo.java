/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.relatorios;

import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author bruno
 */
public class Relatorio_termo {

    public Relatorio_termo(Long i,String imagem) {
        try {
            System.out.println("\n" + i);
            Connection conexao = Utils.open();
            HashMap parametro = new HashMap();
            parametro.put("id", i);
            if(imagem.contains("home")){
                parametro.put("imagem", imagem+"/treinamento/mineiro.png");
            }else{
                parametro.put("imagem", imagem+"\\mineiro.png");
            }
            InputStream caminhorelatorio = getClass().getClassLoader().getResourceAsStream("bac/com/br/hibernate/relatorios/termo.jasper");
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

}
