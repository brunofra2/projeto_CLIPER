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
import java.util.List;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author bruno
 */
public class Multi_impressao {

    public Multi_impressao(Long i,String s,String d,String imagem,String ids) {
        try {
            Connection conexao = Utils.open();
            HashMap parametro = new HashMap();
            parametro.put("id_relatorio", i);
            parametro.put("docs",s);
            parametro.put("seg", d);
            parametro.put("ids",ids);
            if(imagem.contains("home")){
                parametro.put("imagem", imagem+"/treinamento/mineiro.png");
            }else{
                parametro.put("imagem", imagem+"\\mineiro.png");
            }
            InputStream caminhorelatorio = getClass().getClassLoader().getResourceAsStream("bac/com/br/hibernate/relatorios/Multi_impressao.jasper");
                JasperPrint printreport;
               printreport = JasperFillManager.fillReport(caminhorelatorio, parametro,conexao);
            
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

        } catch(NullPointerException ex){
            ex.printStackTrace();
            Msg.alerta(null,"Registro selecionado não tem vinculo com a tabela descricao de cargo");
    }catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "Erro ao Gerar o Relatorio"+e.getMessage());
        }
    }

}
