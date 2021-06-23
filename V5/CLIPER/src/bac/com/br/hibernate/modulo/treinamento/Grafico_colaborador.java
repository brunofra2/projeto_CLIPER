/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author bruno
 */
public class Grafico_colaborador extends javax.swing.JDialog {
    private Tela_principal pai;
    List<BigInteger> lista_documento = new ArrayList<BigInteger>();
    List<BigInteger> lista_grafico = new ArrayList<BigInteger>();
    List<Documento> lista = new ArrayList<Documento>();
    /**
     * Creates new form Grafico_documentos
     */
    public Grafico_colaborador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        grafico();
        executar();
        teste();
        verifica_status();
        criaGraficoPizza();
        Utils.maximizar(this);
    }
    public Grafico_colaborador(Tela_principal parent, boolean modal) {
        super(parent, modal);
        this.pai =parent;
        initComponents();
        grafico();
        executar();
        teste();
        verifica_status();
        criaGraficoPizza();
        Utils.maximizar(this);
    }
    BigInteger a[];
    BigInteger b[];

    private void grafico() {
        try {
            lista_documento = new ColaboradorDao().grafico();
            a = new BigInteger[lista_documento.size()];
            for (int i = 0; i < lista_documento.size(); i++) {
                a[i] = lista_documento.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro \n" + e.getMessage());
        }
    }
    List<String> ad = new ArrayList<String>();
    List<String> add= new ArrayList<String>();
    List<String> estatus= new ArrayList<String>();
    private void executar() {
        for (int i = 0; i < a.length; i++) {
            BigInteger b = a[i];
            String s = String.valueOf(b);
                if(s.equals("null")){
                    ad.add(s);
                }else{
                    add.add(s);
                }
        }
    }
    private void teste(){
        for (int i = 0; i < ad.size(); i++) {
            System.out.println(ad.get(i));
        }
    }
    BigInteger d;
    public void verifica_status(){
        lista_documento.clear();
        for (int i = 0; i < add.size(); i++) {
            d = new ColaboradorDao().estatus(add.get(i));
            if(d != null){
            lista_grafico.add(d);
            }else{
                ad.add(String.valueOf(d));
            }
        }
    }
    public void criaGraficoPizza() {
        DefaultPieDataset cds = createPieDataset();
        String titulo = " Colaboradores";
        JFreeChart graf = ChartFactory.createPieChart3D(titulo, cds, true, true, false);
        PiePlot3D plot = (PiePlot3D) graf.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
        plot.setDepthFactor(0.2);
        plot.setCircular(false);
        plot.setForegroundAlpha(0.5f);
        ChartPanel myChartPanel = new ChartPanel(graf, true);
        myChartPanel.setSize(painel.getWidth(), painel.getHeight());
        myChartPanel.setVisible(true);
        painel.removeAll();
        painel.add(myChartPanel);
        painel.revalidate();
        painel.repaint();
    }

    private DefaultPieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
                    dataset.setValue("COLABORADORES SEM TREINAMENTO", ad.size());
                    dataset.setValue("COLABORADORES TREINADOS", lista_grafico.size());
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro ao Alimentar o Relatorio \nErro:" + e.getMessage());
        }
        return dataset;
    }
    /*
    List<BigInteger> lista_id = new ArrayList<BigInteger>();
    List<String> lista_nome = new ArrayList<String>();
    public void listar_colaboradores(){
        try {
            lista_id = new ColaboradorDao().colaboradores_nulos();
            lista_nome = new ColaboradorDao().colaboradores_nulos2();
            String colaboradores;
            BigInteger a;
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setNumRows(0);
            int sequencia = 1;
            for (int i = 0; i < lista_id.size(); i++) {
                a = lista_id.get(i);
                if(a == null){
                    colaboradores = lista_nome.get(i);
                    model.addRow(new Object[]{
                        sequencia,
                        colaboradores
                    });
                    sequencia++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro "+e.getMessage());
        }
    }
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ESTATISTICA DE TREINAMENTO");
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        painel.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(painel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grafico_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grafico_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grafico_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grafico_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Grafico_colaborador dialog = new Grafico_colaborador(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel painel;
    // End of variables declaration//GEN-END:variables
}
