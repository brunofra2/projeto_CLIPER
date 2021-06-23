/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.modulo.profissiografia;

import bac.com.br.hibernate.modulo.treinamento.*;
import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author bruno
 */
public class Inserir_decricao_cargo extends javax.swing.JDialog {
    private Listar_descricao pai;
    List<Setor> lista_setor = new ArrayList<Setor>();
    List<Funcao> lista_funcao = new ArrayList<Funcao>();
    Descricao des;
    
    private boolean alterar;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
    
    
    /**
     * Creates new form Cadastrar_decricao_cargo
     */
    public Inserir_decricao_cargo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utils.maximizar(this);
        combo_setor();
        combo_cargo();
        iniciar();
        des = new Descricao();
    }
    public Inserir_decricao_cargo(Listar_descricao parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
        combo_setor();
        combo_cargo();
        iniciar();
        des = new Descricao();
    }
    private void iniciar(){
        DESEJAVEL.setDocument(new TextDocument(100));
        MINIMA.setDocument(new TextDocument(100));
        AREA.setDocument(new TextDocument(100));
        EXPERIENCIA.setDocument(new TextDocument(100));
        FAIXA.setDocument(new TextDocument(100));
        HABILITAÇÃO.setDocument(new TextDocument(100));
    }

    private void combo_setor(){
        SETOR.removeAllItems();
        SETOR.addItem("<...>");
        try {
            lista_setor = new DescricaoDao().combo_setor();
            for (Setor s : lista_setor) {
                SETOR.addItem(s.getNomeSetor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro ao carregar setores \n"+e.getMessage());
        }
    }
    private void combo_cargo(){
        CARGO.removeAllItems();
        CARGO.addItem("<...>");
        lista_funcao = new DescricaoDao().combo_cargo();
        for(Funcao f : lista_funcao){
            CARGO.addItem(f.getFuncao());
        }
    }
    private Descricao salvar(){
        des.setArea(AREA.getText().trim());
        des.setDescricaoDetalhada(DESCRICAO.getText().trim());
        des.setEscolaridadeDes(DESEJAVEL.getText().trim());
        des.setEscolaridadeMin(MINIMA.getText().trim());
        des.setExperiencia(EXPERIENCIA.getText().trim());
        des.setFaixaEtaria(FAIXA.getText().trim());
        des.setHabilidade(HABILIDADES.getText().trim());
        des.setHabilitacaoProfissional(HABILITAÇÃO.getText());
        des.setIdFuncaoId(lista_funcao.get(CARGO.getSelectedIndex()-1));
        des.setIdSetorId(lista_setor.get(SETOR.getSelectedIndex()-1));
        des.setLidera(SUBORDINADOS.getText().trim());
        des.setMissao(MISSAO.getText().trim());
        des.setResponsabilidades(RESPONSABILIDADE.getText().trim());
        des.setSexo(SEXO.getSelectedItem().toString().trim());
        des.setSupervisao(SUBORDINACAO.getText().trim());
        des.setTreinamentoLegais(LEGAIS.getText().trim());
        des.setIntegracao("- RH abotdar os quatro pilares: Cultura da empresa, cuidado com a segurança do trabalho"
        + "em equipe e oportunidades na empresa"
        + "- visita a todos os setores da empresa."
        + "- Entregar o Cartão SGQ - Missão, visão e escopo.");
        return des;
    }
    protected void preenche_campos(Descricao des){
        this.des = des;
        AREA.setText(des.getArea());
        DESCRICAO.setText(des.getDescricaoDetalhada());
        DESEJAVEL.setText(des.getEscolaridadeDes());
        MINIMA.setText(des.getEscolaridadeMin());
        EXPERIENCIA.setText(des.getExperiencia());
        FAIXA.setText(des.getFaixaEtaria());
        HABILIDADES.setText(des.getHabilidade());
        HABILITAÇÃO.setText(des.getHabilitacaoProfissional());
        CARGO.setSelectedItem(des.getIdFuncaoId().getFuncao());
        System.out.println(des.getIdSetorId().getId().toString());
        SETOR.setSelectedItem(des.getIdSetorId().getNomeSetor().toString());
        //SETOR.setSelectedIndex(Integer.parseInt(des.getId_setor().getId().toString()));
        SUBORDINADOS.setText(des.getLidera());
        MISSAO.setText(des.getMissao());
        RESPONSABILIDADE.setText(des.getResponsabilidades());
        SEXO.setSelectedItem(des.getSexo());
        SUBORDINACAO.setText(des.getSupervisao());
        LEGAIS.setText(des.getTreinamentoLegais());
        
    }
    private void concluir(){
        if(isAlterar() == false){
            try {
            new DescricaoDao().inserir(salvar());
            Msg.informacao(this, "salvo com sucesso");
            pai.descricoes();
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao salvar \n"+e.getMessage());
        }
        }else{
            try {
                new DescricaoDao().alterar(salvar());
                Msg.informacao(this, "alterado com sucesso");
                pai.descricoes();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao alterar registro \n"+e.getMessage());
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
        CARGO = new componentes.UJComboBox();
        jLabel2 = new javax.swing.JLabel();
        AREA = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SETOR = new componentes.UJComboBox();
        jLabel4 = new javax.swing.JLabel();
        MINIMA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DESEJAVEL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        FAIXA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        SEXO = new componentes.UJComboBox();
        jLabel8 = new javax.swing.JLabel();
        HABILITAÇÃO = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        HABILIDADES = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        EXPERIENCIA = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MISSAO = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DESCRICAO = new javax.swing.JTextPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        RESPONSABILIDADE = new javax.swing.JTextPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        SUBORDINACAO = new javax.swing.JTextPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        SUBORDINADOS = new javax.swing.JTextPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        LEGAIS = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.GridLayout(5, 2));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CARGO:");
        jPanel1.add(jLabel1);

        CARGO.setEditable(true);
        CARGO.setAutocompletar(true);
        CARGO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(CARGO);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AREA:");
        jPanel1.add(jLabel2);

        AREA.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        AREA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AREAActionPerformed(evt);
            }
        });
        jPanel1.add(AREA);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SETOR");
        jPanel1.add(jLabel3);

        SETOR.setEditable(true);
        SETOR.setAutocompletar(true);
        SETOR.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        SETOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SETORActionPerformed(evt);
            }
        });
        jPanel1.add(SETOR);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ESCOLARIDADE MINIMA:");
        jPanel1.add(jLabel4);

        MINIMA.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        MINIMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MINIMAActionPerformed(evt);
            }
        });
        jPanel1.add(MINIMA);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ESCOLARIDADE DESEJAVEL:");
        jPanel1.add(jLabel5);

        DESEJAVEL.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(DESEJAVEL);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FAIXA ETARIA:");
        jPanel1.add(jLabel6);

        FAIXA.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(FAIXA);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SEXO");
        jPanel1.add(jLabel7);

        SEXO.setEditable(true);
        SEXO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<...>", "MASCULINO", "FEMINO", "AMBOS" }));
        SEXO.setAutocompletar(true);
        SEXO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(SEXO);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HABILITAÇÃO PROFICIONAL:");
        jPanel1.add(jLabel8);

        HABILITAÇÃO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(HABILITAÇÃO);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("HABILIDADES:");
        jPanel1.add(jLabel9);

        HABILIDADES.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(HABILIDADES);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("EXPERIENCIA:");
        jPanel1.add(jLabel10);

        EXPERIENCIA.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(EXPERIENCIA);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton2.setText("SALVAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton2);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton3.setText("CANCELAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton3);

        getContentPane().add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("MISSÃO");
        jPanel3.add(jLabel12, java.awt.BorderLayout.NORTH);

        MISSAO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane1.setViewportView(MISSAO);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel3.add(jScrollPane7, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("DESCRIÇÃO DETALHADA");
        jPanel4.add(jLabel13, java.awt.BorderLayout.NORTH);

        DESCRICAO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane2.setViewportView(DESCRICAO);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("RESPONSABILIDADES EXCLUSIVAS DO CARGO");
        jPanel5.add(jLabel14, java.awt.BorderLayout.NORTH);

        RESPONSABILIDADE.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane3.setViewportView(RESPONSABILIDADE);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("SUBORDINAÇÃO HIERARQUICA");
        jPanel6.add(jLabel15, java.awt.BorderLayout.NORTH);

        SUBORDINACAO.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane4.setViewportView(SUBORDINACAO);

        jPanel6.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel16.setText("QUEM SUPERVISIONA OU LIDERA");
        jPanel7.add(jLabel16, java.awt.BorderLayout.NORTH);

        SUBORDINADOS.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane5.setViewportView(SUBORDINADOS);

        jPanel7.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel17.setText("TREINAMENTOS LEGAIS");
        jPanel8.add(jLabel17, java.awt.BorderLayout.NORTH);

        LEGAIS.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jScrollPane6.setViewportView(LEGAIS);

        jPanel8.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel9.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MINIMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MINIMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MINIMAActionPerformed

    private void AREAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AREAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AREAActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      concluir();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SETORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SETORActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SETORActionPerformed

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
            java.util.logging.Logger.getLogger(Inserir_decricao_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inserir_decricao_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inserir_decricao_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inserir_decricao_cargo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Inserir_decricao_cargo dialog = new Inserir_decricao_cargo(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField AREA;
    private componentes.UJComboBox CARGO;
    private javax.swing.JTextPane DESCRICAO;
    private javax.swing.JTextField DESEJAVEL;
    private javax.swing.JTextField EXPERIENCIA;
    private javax.swing.JTextField FAIXA;
    private javax.swing.JTextField HABILIDADES;
    private javax.swing.JTextField HABILITAÇÃO;
    private javax.swing.JTextPane LEGAIS;
    private javax.swing.JTextField MINIMA;
    private javax.swing.JTextPane MISSAO;
    private javax.swing.JTextPane RESPONSABILIDADE;
    private componentes.UJComboBox SETOR;
    private componentes.UJComboBox SEXO;
    private javax.swing.JTextPane SUBORDINACAO;
    private javax.swing.JTextPane SUBORDINADOS;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}
