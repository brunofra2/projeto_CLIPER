/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.EmpresaDao;
import bac.com.br.hibernate.Dao.TreinadorDao;
import bac.com.br.hibernate.Dao.UsuarioDao;
import bac.com.br.hibernate.entidade.Empresa;
import bac.com.br.hibernate.entidade.Treinador;
import bac.com.br.hibernate.entidade.Usuario;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import java.awt.Toolkit;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Cadastrar_treinador extends javax.swing.JDialog {
    private Carregar_treinador pai;
    Treinador tre;
    Usuario user;
    private boolean alterar;
    List<Empresa> lista;
    List<Usuario> lista_usuario;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
    
    
    /**
     * Creates new form Cadastrar_treinador
     */
    public Cadastrar_treinador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.iniciar();
    }
    public Cadastrar_treinador(Carregar_treinador parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        this.iniciar();
        tre = new Treinador();
        atualizacombo();
        atualizacombousuario();
    }

    private void iniciar(){
        nome.setDocument(new TextDocument(50));
        formacao.setDocument(new TextDocument(50));   
    }
    private void atualizacombo(){
        try {
            cbempresa.removeAllItems();
            cbempresa.addItem("<...>");
            lista = new EmpresaDao().getlista("");
            for (Empresa e : lista) {
                cbempresa.addItem(e.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo \nErro :"+e.getMessage());
        }
    }
    
    private void atualizacombousuario(){
        try {
            cbusuario.removeAllItems();
            cbusuario.addItem("<...>");
            lista_usuario = new UsuarioDao().getlista("");
            for (Usuario e : lista_usuario) {
                cbusuario.addItem(e.getCodigo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar combo \nErro :"+e.getMessage());
        }
    }
    private boolean verifica_usuario(){
        boolean vf = new UsuarioDao().verifica_usuario(cbusuario.getSelectedItem().toString(),Long.valueOf(cbusuario.getSelectedIndex()-1));
        return vf;
    }
    protected void preenche(Treinador t){
        this.tre = t;
        nome.setText(t.getNome());
        formacao.setText(t.getFormacao());
        cbempresa.setSelectedItem(t.getEmpresaId().getNome());
        email.setText(t.getEmail());
        supervisor.setText(t.getSupervisor());
        email_supervisor.setText(t.getEmailSupervisor());
    }
    private Treinador salvar(){
        tre.setNome(nome.getText());
        tre.setFormacao(formacao.getText());
        tre.setEmpresaId(lista.get(cbempresa.getSelectedIndex() -1));
        tre.setEmail(email.getText());
        user = new UsuarioDao().seleciona(cbusuario.getSelectedIndex());
        tre.setUsuarioId(user);
        tre.setSupervisor(supervisor.getText());
        tre.setEmailSupervisor(email_supervisor.getText());
        return tre;
    }

    private boolean validacampos() {
        boolean va = true;
        String vazio = "campos vazios\n";
        if (nome.getText().equals("")) {
            va = false;
            vazio += "nome do treinador\n";
        }
        if (formacao.getText().equals("")) {
            va = false;
            vazio += "formaçao do treinador\n";
        }
        if(cbempresa.getSelectedIndex() == 0){
            va = false;
            vazio += "empresa\n";            
        }
        if (va == false) {
            Msg.alerta(this, vazio);
        }
        return va;
    }
    
    private void inserir_alterar(){
        if(verifica_usuario() == true){
            if(validacampos() == true){
            if(isAlterar() == true){
            try {
                new TreinadorDao().alterar(salvar());
                Loggin.arquivo_log("alterado cadastro de um novo treinador com o id"+tre.getId()+
                            " com os itens:"+tre.getNome()+";"+tre.getFormacao()+";"+tre.getEmpresaId());
                Msg.informacao(this, "Alterado com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "Erro ao alterar registro\nErro :"+e.getMessage());
            }
        }else{
            try {
                new TreinadorDao().inserir(salvar());
                Loggin.arquivo_log("criado cadastro de um novo treinador com o id"+tre.getId()+
                            " com os itens:"+tre.getNome()+";"+tre.getFormacao()+";"+tre.getEmpresaId());
                Msg.informacao(this, "Salvo com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "Erro ao salvar registro\nErro :"+e.getMessage());
            }
        }
        pai.atualiza("");
        dispose();
        }
        }else{
            Msg.alerta(this, "este usuario já esta cadastrado como um treinador \n"
                    + "selecione outro usuario ou realize um novo cadastro de usuario");
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

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nome = new javax.swing.JTextField();
        formacao = new javax.swing.JTextField();
        cbempresa = new componentes.UJComboBox();
        email = new javax.swing.JTextField();
        supervisor = new javax.swing.JTextField();
        email_supervisor = new javax.swing.JTextField();
        cbusuario = new componentes.UJComboBox();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridLayout(7, 0, 3, 5));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOME:");
        jPanel2.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FORMAÇÃO:");
        jPanel2.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("EMPRESA:");
        jPanel2.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("EMAIL");
        jPanel2.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SUPERVISOR");
        jPanel2.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EMAIL DO SUPERVISOR");
        jPanel2.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USUARIO");
        jPanel2.add(jLabel7);

        jPanel3.add(jPanel2);

        jPanel1.setLayout(new java.awt.GridLayout(7, 0, 5, 5));

        nome.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });
        jPanel1.add(nome);

        formacao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(formacao);

        cbempresa.setEditable(true);
        cbempresa.setAutocompletar(true);
        cbempresa.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(cbempresa);
        jPanel1.add(email);
        jPanel1.add(supervisor);
        jPanel1.add(email_supervisor);

        cbusuario.setAutocompletar(true);
        cbusuario.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(cbusuario);

        jPanel3.add(jPanel1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton1.setText("SALVAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed

    }//GEN-LAST:event_nomeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       inserir_alterar();
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
            java.util.logging.Logger.getLogger(Cadastrar_treinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cadastrar_treinador dialog = new Cadastrar_treinador(new javax.swing.JFrame(), true);
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
    private componentes.UJComboBox cbempresa;
    private componentes.UJComboBox cbusuario;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email_supervisor;
    private javax.swing.JTextField formacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField supervisor;
    // End of variables declaration//GEN-END:variables
}
