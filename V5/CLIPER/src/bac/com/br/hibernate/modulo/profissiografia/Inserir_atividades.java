/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.modulo.profissiografia;

import bac.com.br.hibernate.Dao.AtividadeDao;
import bac.com.br.hibernate.modulo.treinamento.*;
import bac.com.br.hibernate.Dao.FuncaoDao;
import bac.com.br.hibernate.Dao.LotacaoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.entidade.Atividades;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.entidade.Lotacao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class Inserir_atividades extends javax.swing.JDialog {
    private Listar_atividades pai;
    Funcao fun;    
    Atividades atividades;
    List<Setor> lista_setor = new ArrayList<Setor>();
    List<Lotacao> lista_local = new ArrayList<Lotacao>();
    private boolean alterar;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
    
    /**
     * Creates new form Cadastrar_funcao
     */
    public Inserir_atividades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        atividades = new Atividades();
        initComponents();
        this.iniciar();
    }
    public Inserir_atividades(Listar_atividades parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        atividades = new  Atividades();
        initComponents();
        this.iniciar();
    }
    private void atualizacombo(){
        try {
       //     setor.removeAllItems();
       //     setor.addItem("<...>");
            lista_setor = new SetorDao().getlista("");
            for (Setor s : lista_setor) {
        //        setor.addItem(s.getNomeSetor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo \n"+e.getMessage());
        }
    }
    private void atualizacombo2(){
        try {
        //    local.removeAllItems();
        //    local.addItem("<...>");
            lista_setor = new LotacaoDao().getlista("");
            for (Setor s : lista_setor) {
       //         setor.addItem(s.getNomeSetor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo \n"+e.getMessage());
        }
    }
    private void iniciar(){
//        funcao.setDocument(new TextDocument(100));
       // atualizacombo();
       // atualizacombo2();
    }
    protected void preenchecampo(Atividades f){
        atividades = f;
        descricao.setText(atividades.getDescricao());
        complemento.setText(atividades.getComplemento());
    }
    private Atividades salvar(){
        atividades.setDescricao(descricao.getText().trim());
        atividades.setComplemento(complemento.getText());
        atividades.setIdCargo(fun);
        return atividades;
    }

  /*  private boolean validacampos() {
        boolean va = true;
        String vazio = "campos vazios\n";
    //    if (funcao.getText().equals("")) {
            va = false;
            vazio += "nome do cargo\n";
        }
        if (setor.getSelectedIndex() == 0) {
            va = false;
            vazio += "nome do setor\n";
        }if (situacao.getSelectedIndex() == 0) {
            va = false;
            vazio += "nome do situacao\n";
        }if (local.getSelectedIndex() == 0) {
            va = false;
            vazio += "nome do situacao\n";
        }
        if (va == false) {
            Msg.alerta(this, vazio);
        }
        return va;
    }
    }*/
    private void inserir_alterar(){
       // if(validacampos() == true){
            if(isAlterar() == true){
            try {
                new AtividadeDao().alter(salvar());
                Loggin.arquivo_log("alterado cadastro de uma nova funçao com o id"+fun.getId()+
                            " com os itens:"+fun.getFuncao()+";"+fun.getSetorId());
                pai.atualizar("");
                Msg.informacao(this, "registro alterado com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao realizar alterações no registro selecionado\nErro: "+e.getMessage());
            }
        }else{
            try {
                new AtividadeDao().insert(salvar());
                Loggin.arquivo_log("criado cadastro de um novo documento com o id"+fun.getId()+
                            " com os itens:"+fun.getFuncao()+";"+fun.getSetorId());
                pai.atualizar("");
                Msg.informacao(this, "salvo com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao inserir registro\nErro: "+e.getMessage());
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        complemento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

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

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.GridLayout(3, 0, 5, 5));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DESCRIÇÃO");
        jPanel3.add(jLabel5);

        descricao.setColumns(20);
        descricao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        descricao.setRows(5);
        jScrollPane2.setViewportView(descricao);

        jPanel3.add(jScrollPane2);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("COMPLEMENTO");

        complemento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        complemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complementoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
        );

        jPanel3.add(jPanel4);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       inserir_alterar();
    dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void complementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_complementoActionPerformed

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
            java.util.logging.Logger.getLogger(Inserir_atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inserir_atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inserir_atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inserir_atividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Inserir_atividades dialog = new Inserir_atividades(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField complemento;
    private javax.swing.JTextArea descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
