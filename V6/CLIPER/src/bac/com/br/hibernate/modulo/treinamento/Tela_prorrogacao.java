/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.Dao.UsuarioDao;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import bac.com.br.hibernate.utils.enviar_email;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Tela_prorrogacao extends javax.swing.JDialog {
    private Carregar_treinamento pai;
    protected Treinamento treinamento;
    List<Object[]> listausuario;
    /**
     * Creates new form Tela_prorrogacao
     */
    public Tela_prorrogacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utils.maximizar(this);
    }
    public Tela_prorrogacao(Carregar_treinamento parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
    }
    
    public void ler_texto(){
        try {
            Scanner sc = new Scanner(new FileReader("conf.txt"));
            int a = 0;
            String id = null;
            String codigo = null;
            while (sc.hasNextLine()) {      
                if(a == 0){
                    id = sc.nextLine();
                }
                if(a == 1){
                    codigo = sc.nextLine();
                }
                a++;
            }
                System.out.println(id);
                System.out.println(codigo);
                listausuario = new UsuarioDao().prorrogar(codigo, id);
                
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao ler arquivo \n"+e.getMessage());
        }
    }
    public boolean vazio(){
        boolean vazio = false;
        String texto = "campo vazio \n";
        if(justificativa.getText().equals("")){
            texto+="justificativa";
            vazio = true;
        }
        if(vazio == true){
            Msg.alerta(this, texto);
        }
        return vazio;
    }
    public boolean treinamento_prorrogavel(){
        boolean teste = false;
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String da = formato.format(data.getDate());
        
        System.out.println("data limite: "+da);
        System.out.println("idpai"+treinamento.getId());
        List<Integer> limite = new TreinamentoDao().prorrogavel(treinamento.getId(),da);
        System.out.println("\n lista de limite:"+limite.size());
        if(limite.size() > 0){
            teste = true;
        }else{
            teste = false;
        }
        return teste;
    }
    
     public boolean treinamento_vencendo(){
        boolean teste = false;
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String da = formato.format(data.getDate());
        
        System.out.println("data limite: "+da);
        System.out.println("idpai"+treinamento.getId());
        List<Integer> limite = new TreinamentoDao().prorrogavel(treinamento.getId());
        System.out.println("\n lista de limite:"+limite.size());
        if(limite.size() > 0){
            teste = true;
        }else{
            teste = false;
        }
        return teste;
    }
    
    public boolean limitar(){
        boolean teste = false;
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String da = formato.format(data.getDate());
        
        System.out.println("data limite: "+da);
        System.out.println("idpai"+treinamento.getId());
        List<Integer> limite = new TreinamentoDao().limite(treinamento.getId(),da);
        System.out.println("\n lista de limite:"+limite.size());
        if(limite.size() > 0){
            teste = true;
        }else{
            teste = false;
        }
        return teste;
    }
    public void enviar_email(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
        String da = formato.format(this.data.getDate());
        String mensagem = "";
        mensagem+="Esta Mensagem foi enviada automaticamente "
                    + "pelo software de treinamento \n"
                    + "para informar que o treinamento nº "+treinamento.getId()+",\n"
                    + "foi prorrogado o seu vencimento para a data "+da+", \n";
                    
        String assunto = "PRORROGAÇÃO DE DATA ";
        for (Object[] s : listausuario) {
                        mensagem+="pelo colaborador "+s[0]+", usando a seguinte justificativa:\n"
                                  + "_________________JUSTIFICATIVA__________________________\n"
                                  +justificativa.getText()+"\n"
                                  + "________________________________________________________\n";
                        mensagem+="Enviada uma cópia para seu supervisor\n"
                                    + "Att. \n"
                                    + "Bruno Alves Carneiro\n"
                                    + "Analista de Sistemas\n"
                                    + "Desenvolvedor do software de treinamento \n"
                                    + "contatos: (41)3627-1711 /brunoalvesfra@gmail.com";
                        new enviar_email().emailsimples(mensagem, String.valueOf(s[1]), assunto, String.valueOf(s[2]));
                }
    }
    public Treinamento prorrogar(){
        treinamento.setStatus("PRORROGADO");
        treinamento.setProrrogacao(justificativa.getText());
        treinamento.setProxTreinamento(data.getDate());
        return treinamento;
    }
    
    public void salvar(){
        if(vazio() == false){
            ler_texto();
            new TreinamentoDao().alterar(prorrogar());
            enviar_email();
            pai.atualizar();
            dispose();
            Msg.informacao(this, "treinamento prorrogado para a data selecionada");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        justificativa = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        data = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JUSTIFICATIVA");
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        justificativa.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(justificativa);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jLabel2.setText("data de prorrogação:");
        jPanel3.add(jLabel2);
        jPanel3.add(data);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton1.setText("SALVAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*if(treinamento_vencendo() == true){
            
        }else{
            Msg.alerta(this, "treinamento selecionado não pode ser prorrogado. \n"
                    + "porque o seu periodo de prorrogação não foi atingido \n"
                    + "espere chegar em sua caixa de email a mensagem de reciclagem \n"
                    + "referente a este treinamento \n"
                    + "para que possa prorroga-lo.");
        }*/
        if(treinamento_prorrogavel() == true){
                if(limitar() == true){
                    salvar();
                }else{
                    Msg.alerta(this, "data para prorrogação selecionada \n"
                            + "não permitida, deve ser selecionado uma data \n"
                            + "que não exceda a data limite de 90 dias\n");
                }
        }else{
            Msg.alerta(this, "data seleciona é uma data anterior \n"
                    + "há data de vencimento do treinamento");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_prorrogacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_prorrogacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_prorrogacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_prorrogacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela_prorrogacao dialog = new Tela_prorrogacao(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXDatePicker data;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane justificativa;
    // End of variables declaration//GEN-END:variables
}
