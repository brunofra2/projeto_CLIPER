/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.avaliacao;
  
import bac.com.br.hibernate.Dao.AvaliacaoDao;
import bac.com.br.hibernate.entidade.Avaliacao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.utils.Msg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class aprovacoes extends javax.swing.JDialog {

    Avaliacao ava;
    private Av_principal pai;
    List<Colaborador> lista = new ArrayList<Colaborador>();
    List<Funcao> lista_fun = new ArrayList<Funcao>();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    List<Avaliacao> lista_ver = new ArrayList<>();
    private boolean alterar;

    /**
     * Creates new form adicao_colaborador
     */
    public aprovacoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        combo_cargo();
        ava = new Avaliacao();
    }

    public aprovacoes(Av_principal parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        combo_cargo();
        ava = new Avaliacao();
    }

    public void combo_cargo() {
        //lista_fun = new AvaliacaoDao().getcargo();
        //cargo_proposto.removeAllItems();
        //cargo_proposto.addItem("<...>");
        for (Funcao f : lista_fun) {
            //     cargo_proposto.addItem(f.getFuncao());
        }
    }

    public void salvar_avaliacao() {
        if (isAlterar() == true) {
            new AvaliacaoDao().alterar(salvar_exames());
            Msg.informacao(this, "alterado com sucesso");
        } else {
            Msg.alerta(this, "este colaborador não contém avaliação pendente");
        }
    }

    /*public Avaliacao salvar(){
            ava.setIdColaborador(lista.get(0));
            ava.setIdCargoAtual(lista.get(0).getFuncaoId());
            ava.setTempoAtual(tempo_atual.getText());
            ava.setSalarioAtual(Double.parseDouble(salario_atual.getText()));
            ava.setIdCargo(lista_fun.get(cargo_proposto.getSelectedIndex() - 1));
            ava.setSalarioProposto(Double.parseDouble(salario_proposto.getText()));
            ava.setDataAvaliacao(data_avaliacao.getDate());
            ava.setFimExperiencia(data_avaliacao.getDate());
            ava.setEstatusAvaliacao("AVALIAÇÃO");
            return ava;
    }*/
    public Avaliacao salvar_exames() {
        ava = lista_ver.get(0);
        ava.setDataSup(data_supervisao.getDate());
        ava.setParecerSup(texto_supervisor.getText());
        if (APROVADO.isSelected() == true) {
            ava.setSituacao("APROVADO");
            ava.setDataGen(data_gerencia.getDate());
            ava.setDataRh(data_rh.getDate());
            ava.setParecerGen(texto_gerencia.getText());
            ava.setParecerRh(texto_rh.getText());
        }
        if (REPROVADO.isSelected() == true) {
            ava.setSituacao("REPROVADO");
        }
        if (ava.getSituacao().equals("REPROVADO")) {
            ava.setEstatusAvaliacao("REPROVADO");
        } else {
            ava.setEstatusAvaliacao("APROVACAO");
        }
        return ava;
    }

    public void buscar_colaborador(String texto) {
        try {
            lista = new AvaliacaoDao().getcolaborador(texto);
            nome.setText(lista.get(0).getNome());
            admissao.setText(format.format(lista.get(0).getDataAdm()));
            cargo_atual.setText(lista.get(0).getFuncaoId().getFuncao());

            lista_ver = new AvaliacaoDao().getavaliaca(lista.get(0).getId());
            if (lista_ver.size() > 0) {
                String a = lista_ver.get(0).getIdColaborador().getId().toString();
                String b = lista.get(0).getId().toString();
                System.out.println(a);
                System.out.println(b);
                if (a.equals(b)) {
                    setAlterar(true);
                    preenchecampos(lista_ver.get(0));
                } else {
                    setAlterar(false);
                }
            } else {
                setAlterar(false);
                Msg.alerta(this, "este colaborador não contém avaliação ativa"
                        + ", para prosseguir crie uma nova avaliação");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro " + e.getMessage());
        }
    }

    public void preenchecampos(Avaliacao av) {
        // AVALIAÇÃO
        try {
            ava = av;
            registro.setText(av.getIdColaborador().getRegistro());
            nome.setText(av.getIdColaborador().getNome());
            cargo_atual.setText(av.getIdCargoAtual().getFuncao());
            cargo_proposto.setText(av.getIdCargo().getFuncao());
            salario_proposto.setText(av.getSalarioProposto().toString());
            if (lista_ver.size() > 0) {
                if(av.getDataSup() != null){
                data_supervisao.setDate(av.getDataSup());
                texto_supervisor.setText(av.getParecerSup());
                if (av.getSituacao().contains("APROVADO")) {
                    APROVADO.setSelected(true);
                    data_gerencia.setDate(av.getDataGen());
                    data_rh.setDate(av.getDataRh());
                    texto_gerencia.setText(av.getParecerGen());
                    texto_rh.setText(av.getParecerRh());
                } else {
                    REPROVADO.setSelected(true);
                }
                
                }
            }
            //admissao.setText(format.format(av.getIdColaborador().getDataAdm()));
            //tempo_atual.setText(av.getTempoAtual());
            //salario_atual.setText(av.getSalarioAtual().toString());
            // EXAME
        } catch (Exception e) {
            e.printStackTrace();
            Msg.alerta(this, "não existe dados de aprovação, insira - os para continuar");
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
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        registro = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        admissao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cargo_atual = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cargo_proposto = new javax.swing.JLabel();
        salario_proposto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        data_supervisao = new org.jdesktop.swingx.JXDatePicker();
        APROVADO = new javax.swing.JRadioButton();
        REPROVADO = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto_supervisor = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        texto_gerencia = new javax.swing.JTextArea();
        data_gerencia = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        data_rh = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane3 = new javax.swing.JScrollPane();
        texto_rh = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("AVALIAÇÃO");
        jPanel1.add(jLabel8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jLabel1.setText("REGISTRO:");

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("NOME:");

        jLabel3.setText("ADMISSÃO:");

        jLabel5.setText("CARGO ATUAL:");

        jLabel10.setText("CARGO PROPOSTO:");

        jLabel11.setText("SALARIO PROPOSTO:");

        jLabel4.setText("PARECER SUPERVISOR:");

        APROVADO.setText("APROVADO");
        APROVADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                APROVADOActionPerformed(evt);
            }
        });

        REPROVADO.setText("REPROVADO");
        REPROVADO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REPROVADOActionPerformed(evt);
            }
        });

        texto_supervisor.setColumns(20);
        texto_supervisor.setRows(5);
        jScrollPane1.setViewportView(texto_supervisor);

        jLabel7.setText("PARECER DA GERENCIA:");

        texto_gerencia.setColumns(20);
        texto_gerencia.setRows(5);
        jScrollPane2.setViewportView(texto_gerencia);

        jLabel6.setText("PARECER DO RH:");

        texto_rh.setColumns(20);
        texto_rh.setRows(5);
        jScrollPane3.setViewportView(texto_rh);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(admissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(data_supervisao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargo_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salario_proposto, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(REPROVADO)
                            .addComponent(APROVADO))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargo_atual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(data_gerencia, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(data_rh, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(admissao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cargo_atual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargo_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(data_supervisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(salario_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(APROVADO)
                        .addGap(18, 18, 18)
                        .addComponent(REPROVADO))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(data_gerencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(data_rh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        jButton2.setText("SALVAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("SAIR");
        jPanel2.add(jButton3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(541, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar_colaborador(registro.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvar_avaliacao();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void APROVADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_APROVADOActionPerformed
       APROVADO.setSelected(true);
       REPROVADO.setSelected(false);
    }//GEN-LAST:event_APROVADOActionPerformed

    private void REPROVADOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REPROVADOActionPerformed
        APROVADO.setSelected(false);
        REPROVADO.setSelected(true);
    }//GEN-LAST:event_REPROVADOActionPerformed

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
            java.util.logging.Logger.getLogger(aprovacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aprovacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aprovacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aprovacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                aprovacoes dialog = new aprovacoes(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton APROVADO;
    private javax.swing.JRadioButton REPROVADO;
    private javax.swing.JLabel admissao;
    private javax.swing.JLabel cargo_atual;
    private javax.swing.JLabel cargo_proposto;
    private org.jdesktop.swingx.JXDatePicker data_gerencia;
    private org.jdesktop.swingx.JXDatePicker data_rh;
    private org.jdesktop.swingx.JXDatePicker data_supervisao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nome;
    private javax.swing.JTextField registro;
    private javax.swing.JLabel salario_proposto;
    private javax.swing.JTextArea texto_gerencia;
    private javax.swing.JTextArea texto_rh;
    private javax.swing.JTextArea texto_supervisor;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the alterar
     */
    public boolean isAlterar() {
        return alterar;
    }

    /**
     * @param alterar the alterar to set
     */
    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
}
