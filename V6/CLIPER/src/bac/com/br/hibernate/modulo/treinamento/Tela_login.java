/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.BackupDao;
import bac.com.br.hibernate.Dao.UsuarioDao;
import bac.com.br.hibernate.entidade.Usuario;
import bac.com.br.hibernate.entidade.Backup;
import bac.com.br.hibernate.modulo.profissiografia.Tela_principalP;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import bac.com.br.hibernate.utils.transmissao;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 *
 * @author bruno
 */
public class Tela_login extends javax.swing.JFrame {

    protected Long usuario;
    private String tipo;
    Usuario us;
    private Integer s;
    private Integer b;
    Backup bac;
    /**
     * Creates new form Tela_login
     */
    public Tela_login() {
        this.iniciar();
        initComponents();
        us = new Usuario();
        bac = new Backup();
    }

    // INICIA A TELA
    private void iniciar() {
        Utils.tanscomponente(this);
        setResizable(false);
    }

    // VERIFICAR O LOGIN DO USUARIO
    private void verificarlogin() {
        try {
            us = new UsuarioDao().login(USUARIO.getText(), SENHA.getText().toString());
            if (us.getId() != -1) {
                tipo = us.getTipo();
                usuario = us.getId();
                apagar_usuario();
                capturar_usuario(us);
                if(usuario() == 0){
                Tela_principal p = new Tela_principal();
                p.setVisible(true);
                }
                if (usuario() == 1) {
                    Tela_principal t = new Tela_principal();
                    t.colaborador.setEnabled(false);
                    t.cargo.setEnabled(false);
                    t.empresa.setEnabled(false);
                    t.treinador.setEnabled(false);
                    t.usuario.setEnabled(false);
                    t.setores.setEnabled(false);
                    t.pgr.setEnabled(false);
                t.setVisible(true);
                }
                if(usuario() == 2){
                    Tela_principalP pro = new Tela_principalP();
                    pro.setVisible(true);
                }
                /*
                Writer a = new FileWriter("app_info.log");
                a.write("");
                a.flush();*/
                Loggin.arquivo_log("realisado login com o usuario "+USUARIO.getText());
                dispose();
            } else {
                Msg.alerta(this, "usuario ou senha incorretos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage().contains("[AWT")){
                Msg.erro(this, "erro"+e.getMessage());
            }
            Msg.erro(this, "erro ao verificar login \n " + e.getMessage());
        }
    }
    private void apagar_usuario(){
        File file = new File("conf.txt");
        file.delete();
    }
    private void capturar_usuario(Usuario user){
        try {
            FileWriter file = new FileWriter(new File("conf.txt"));
                file.write(user.getId().toString()+"\n");
                file.write(user.getCodigo());
                file.close();
        } catch (Exception ex) {
            Msg.erro(this, "erro ao criar arquivo \n"+ex.getMessage());
        }
    }
    // SETA AS PERMISSÕES CORRETAS
    private int usuario() {
        int v = 0;
        try {
            if (tipo.equals("ADMINISTRADOR")) {
                System.out.print("administrador");
                v = 0;
            }
            if (tipo.equals("COMUM")) {
                System.out.println("comum");
                v = 1;
            }
            if(tipo.equals("PPP")){
                v= 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao setar privilegios \n favor reinicie o programa \n" + e.getMessage());
        }
        return v;
    }

   /* private void realizar_backup() {
        try {
            s = new BackupDao().ultimo_backup();
            if (s == 0) {
                new Realiza_backup(this, true);
            } else {
                if (s != null) {
                    String c = new BackupDao().diferenca_dias(s);
                    b = Integer.parseInt(c);
                    if (b >= 7) {
                        new Realiza_backup(this, true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao realizar backup \n" + e.getMessage());
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        USUARIO = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SENHA = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("tela de login");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 0, 5));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/user_red.png"))); // NOI18N
        jLabel1.setText("USUARIO:");
        jPanel2.add(jLabel1);

        USUARIO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jPanel2.add(USUARIO);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/key.png"))); // NOI18N
        jLabel2.setText("SENHA:");
        jPanel2.add(jLabel2);

        SENHA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jPanel2.add(SENHA);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/texto.png"))); // NOI18N
        jLabel3.setText("SEJA BEM VINDO");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel3);

        getContentPane().add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/lock_open.png"))); // NOI18N
        jButton1.setText("ENTRAR");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });
        jPanel3.add(jButton2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(329, 262));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        carregar();
        verificarlogin();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // verificarlogin();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        dispose();
    }//GEN-LAST:event_jButton2KeyPressed

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
            java.util.logging.Logger.getLogger(Tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField SENHA;
    private javax.swing.JTextField USUARIO;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
private void carregar() {
      final ProgressBar1 p1 = new ProgressBar1("Carregando", this);
      SwingWorker worker = new SwingWorker() {
      @Override
      protected Object doInBackground() throws Exception {
      p1.gerarJanela();
          System.out.println("gerada");
      Thread.sleep(3000);
      return null;
      }
        @Override
        protected void done() {
        p1.fechar();
        }
        };
        worker.execute();
    }
}
