/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.modulo.profissiografia;

import bac.com.br.hibernate.Dao.DomicilioDao;
import bac.com.br.hibernate.modulo.treinamento.*;
import bac.com.br.hibernate.Dao.FuncaoDao;
import bac.com.br.hibernate.Dao.LotacaoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.entidade.Domicilio;
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
public class Inserir_cargo extends javax.swing.JDialog {
    private Listar_atividades pai;
    Funcao fun;    
    List<Setor> lista_setor = new ArrayList<Setor>();
    List<Lotacao> lista_local = new ArrayList<Lotacao>();
    List<Domicilio> lista_do  = new ArrayList<Domicilio>();
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
    public Inserir_cargo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.iniciar();
    }
    public Inserir_cargo(Listar_atividades parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        this.iniciar();
        fun = new Funcao();
    }
    private void atualizacombo(){
        try {
            setor.removeAllItems();
            setor.addItem("<...>");
            lista_setor = new SetorDao().getlista("");
            for (Setor s : lista_setor) {
                setor.addItem(s.getNomeSetor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo 1 \n"+e.getMessage());
        }
    }
    private void atualizacombo2(){
        try {
            local.removeAllItems();
            local.addItem("<...>");
            lista_local = new LotacaoDao().getlista("");
            for (Lotacao s : lista_local) {
                local.addItem(s.getTitulo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo 2 \n"+e.getMessage());
        }
    }
    
    private void atualizacombo3(){
        try {
            domicilio.removeAllItems();
            domicilio.addItem("<...>");
            lista_do = new DomicilioDao().getlista("");
            for (Domicilio s : lista_do) {
                domicilio.addItem(s.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo 3 \n"+e.getMessage());
        }
    }
    private void iniciar(){
        funcao.setDocument(new TextDocument(100));
        atualizacombo();
        atualizacombo2();
        atualizacombo3();
    }
    protected void preenchecampo(Funcao f){
        fun = f;
        funcao.setText(f.getFuncao());
        setor.setSelectedItem(f.getIdSetor().getNomeSetor());
        situacao.setSelectedItem(f.getCondicao());
        local.setSelectedItem(f.getLotacaoId().getTitulo());
        domicilio.setSelectedItem(f.getDomicilioId().getNome());
        
    }
    private Funcao salvar(){
        fun.setFuncao(funcao.getText());
        fun.setIdSetor(lista_setor.get(setor.getSelectedIndex()-1));
        fun.setCondicao(situacao.getSelectedItem().toString());
        fun.setLotacaoId(lista_local.get(local.getSelectedIndex()-1));
        fun.setDomicilioId(lista_do.get(domicilio.getSelectedIndex()-1));
        return fun;
    }

    private boolean validacampos() {
        boolean va = true;
        String vazio = "campos vazios\n";
        if (funcao.getText().equals("")) {
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
    private void inserir_alterar(){
        if(validacampos() == true){
            if(isAlterar() == true){
            try {
                new FuncaoDao().alterar(salvar());
                Loggin.arquivo_log("alterado cadastro de uma nova funçao com o id"+fun.getId()+
                            " com os itens:"+fun.getFuncao()+";"+fun.getIdSetor());
                pai.atualizar("");
                Msg.informacao(this, "registro alterado com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao realizar alterações no registro selecionado\nErro: "+e.getMessage());
            }
        }else{
            try {
                new FuncaoDao().inserir(salvar());
                Loggin.arquivo_log("criado cadastro de um novo documento com o id"+fun.getId()+
                            " com os itens:"+fun.getFuncao()+";"+fun.getIdSetor());
               
                Msg.informacao(this, "salvo com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao inserir registro\nErro: "+e.getMessage());
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        funcao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        setor = new componentes.UJComboBox();
        jLabel3 = new javax.swing.JLabel();
        situacao = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        local = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        domicilio = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.GridLayout(5, 2, 5, 5));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOME DO CARGO:");
        jPanel1.add(jLabel1);

        funcao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(funcao);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SETOR:");
        jPanel1.add(jLabel2);

        setor.setEditable(true);
        setor.setAutocompletar(true);
        setor.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        setor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setorActionPerformed(evt);
            }
        });
        jPanel1.add(setor);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SITUAÇÃO");
        jPanel1.add(jLabel3);

        situacao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        situacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<...>", "ATIVO", "ANTIGAS" }));
        jPanel1.add(situacao);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOCAL DE TRABALHO");
        jPanel1.add(jLabel4);

        local.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        local.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(local);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("EMPRESA:");
        jPanel1.add(jLabel5);

        domicilio.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        domicilio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(domicilio);

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

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

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

    private void setorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setorActionPerformed

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
            java.util.logging.Logger.getLogger(Inserir_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inserir_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inserir_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inserir_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Inserir_cargo dialog = new Inserir_cargo(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox domicilio;
    private javax.swing.JTextField funcao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox local;
    private componentes.UJComboBox setor;
    private javax.swing.JComboBox situacao;
    // End of variables declaration//GEN-END:variables
}
