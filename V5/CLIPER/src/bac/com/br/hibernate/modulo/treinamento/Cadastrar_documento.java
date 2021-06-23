/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.Dao.Tipo_treinamentoDao;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.TipoTreinamento;
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
public class Cadastrar_documento extends javax.swing.JDialog {

    private Carregar_Documentos pai;
    private Cadastrar_treinamento tio;
    Documento doc;
    Setor set;
    TipoTreinamento tipos;
    private boolean alterar;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    /**
     * Creates new form Cadastrar_documento
     */
    public Cadastrar_documento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.iniciar();
        doc = new Documento();
        set = new Setor();
        tipos = new TipoTreinamento();
    }

    public Cadastrar_documento(Carregar_Documentos parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        this.iniciar();
        doc = new Documento();
        set = new Setor();
    }
    public Cadastrar_documento(Cadastrar_treinamento sobrinho, boolean modal) {
        super(sobrinho, modal);
        this.tio = sobrinho;
        initComponents();
        this.iniciar();
        doc = new Documento();
        set = new Setor();
    }

    private void iniciar(){
        titulo.setDocument(new TextDocument(500));
        tipo.setDocument(new TextDocument(100));  
        atualizacombo();
        atualzartipo();
    }
    List<Setor> lista_setor = new ArrayList<Setor>();
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
            Msg.erro(this, "erro ao atualizar combo \n"+e.getMessage());
        }
    }
    
    List<TipoTreinamento> lista_tipo = new ArrayList<TipoTreinamento>();
    private void atualzartipo(){
        try {
            TIPO.removeAllItems();
            TIPO.addItem("<...>");
            lista_tipo = new Tipo_treinamentoDao().combo();
            for (TipoTreinamento ti : lista_tipo) {
                TIPO.addItem(ti.getTipoTreinamento());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this,"erro ao atualizar combo tipo \n"+e.getMessage());
        }
    }
    protected void preenchecampos(Documento d) {
        doc = d;
        titulo.setText(doc.getTitulo());
        tipo.setText(doc.getTipo());
        setor.setSelectedItem(doc.getSetorId().getNomeSetor());
        TIPO.setSelectedItem(doc.getTipoTreinamentoId().getTipoTreinamento());
    }

    private boolean validacampos() {
        boolean va = true;
        String vazio = "campos vazios\n";
        if (titulo.getText().equals("")) {
            va = false;
            vazio += "nome do documento\n";
        }
        if (tipo.getText().equals("")) {
            va = false;
            vazio += "tipo do documento\n";
        }
        if (setor.getSelectedIndex() == 0) {
            va = false;
            vazio += "setor";
        }
        if(TIPO.getSelectedIndex() == 0){
            va = false;
            vazio += "tipo de treinamento";
        }
        if (va == false) {
            Msg.alerta(this, vazio);
        }
        return va;
    }

    private Documento salvar() {
        doc.setTitulo(titulo.getText());
        doc.setTipo(tipo.getText());
        doc.setSetorId(lista_setor.get(setor.getSelectedIndex()-1));
        doc.setTipoTreinamentoId(lista_tipo.get(TIPO.getSelectedIndex()-1));
        doc.setModificacao("ATIVO");
        return doc;
    }

    private void inserir_alterar() {
        if (validacampos() == true) {
            if (isAlterar() == true) {
                try {
                    new DocumentoDao().alterar(salvar());
                    Loggin.arquivo_log("alterado cadastro de um novo documento com o id"+doc.getId()+
                            " com os itens:"+doc.getTitulo()+";"+doc.getTipo()+";"+doc.getModificacao()+";"+doc.getSetorId());
                    pai.atualizatabela("");
                    Msg.informacao(this, "alterado com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "Erro ao realizar alteração no registro!\nErro: " + e.getMessage());
                }

            } else {
                try {
                    new DocumentoDao().inserir(salvar());
                    Loggin.arquivo_log("criado cadastro de um novo documento com o id"+doc.getId()+
                            " com os itens:"+doc.getTitulo()+";"+doc.getTipo()+";"+doc.getModificacao()+";"+doc.getSetorId());
                    if(pai != null){
                         pai.atualizatabela("");
                    }
                    Msg.informacao(this, "salvo com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "Erro ao salvar registro!\nErro: " + e.getMessage());
                }
            }

            dispose();
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
        titulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        setor = new componentes.UJComboBox();
        jLabel4 = new javax.swing.JLabel();
        TIPO = new componentes.UJComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TITULO:");
        jPanel1.add(jLabel1);

        titulo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jPanel1.add(titulo);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TIPO:");
        jPanel1.add(jLabel2);

        tipo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });
        jPanel1.add(tipo);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SETOR");
        jPanel1.add(jLabel3);

        setor.setEditable(true);
        setor.setAutocompletar(true);
        setor.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(setor);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TIPO TREINAMENTO:");
        jPanel1.add(jLabel4);

        TIPO.setEditable(true);
        TIPO.setAutocompletar(true);
        TIPO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(TIPO);

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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastrar_documento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_documento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_documento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_documento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cadastrar_documento dialog = new Cadastrar_documento(new javax.swing.JFrame(), true);
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
    private componentes.UJComboBox TIPO;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private componentes.UJComboBox setor;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField titulo;
    // End of variables declaration//GEN-END:variables
}
