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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class adicao_colaborador extends javax.swing.JDialog {

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
    public adicao_colaborador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        combo_cargo();
        ava = new Avaliacao();
    }

    public adicao_colaborador(Av_principal parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        combo_cargo();
        ava = new Avaliacao();
    }

    public void combo_cargo() {
        lista_fun = new AvaliacaoDao().getcargo();
        cargo_proposto.removeAllItems();
        cargo_proposto.addItem("<...>");
        for (Funcao f : lista_fun) {
            cargo_proposto.addItem(f.getFuncao());
        }
    }

    public void salvar_avaliacao() {
        if (isAlterar() == true) {
            new AvaliacaoDao().alterar(salvar());
            Msg.informacao(this, "alterado com sucesso");
        } else {
            new AvaliacaoDao().incluir(salvar());
            Msg.informacao(this, "salvo com sucesso");
        }
    }

    public Avaliacao salvar() {
        ava.setIdColaborador(lista.get(0));
        ava.setIdCargoAtual(lista.get(0).getFuncaoId());
        ava.setTempoAtual(tempo_atual.getText());
        ava.setSalarioAtual(Double.parseDouble(salario_atual.getText().replace(",", ".")));
        ava.setIdCargo(lista_fun.get(cargo_proposto.getSelectedIndex() - 1));
        ava.setSalarioProposto(Double.parseDouble(salario_proposto.getText().replace(",", ".")));
        ava.setDataAvaliacao(data_avaliacao.getDate());
        ava.setFimExperiencia(data_avaliacao.getDate());
        ava.setExames("*");
        ava.setTreinamentos("*");
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String texto = "2012-07-07";
        Date data = null;
        
        try {
            data = formato.parse(texto);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        ava.setDataGen(data);
        ava.setDataSup(data);
        ava.setDataRh(data);
        ava.setEstatusAvaliacao("AVALIAÇÃO");
        return ava;
    }

    /*
    public void salvar_exames() {
        if(isAlterar() == true){
            
                preenchecampo2(lista_ver.get(0));
        }else{
            ava.setDataExames(data_exame.getDate());
            String exames = "";
            String resultado = "";
            if(audiometria.isSelected() == true){
                exames +="audiometria,";
            }
            if (espirometria.isSelected() == true) {
                exames +="espirometria,";
            }
            if(acuidade.isSelected() == true){
                exames +="acuidade visual,";
            }
            if(rx.isSelected() == true){
                exames +="RX DE TORAX";
            }
            if(apto.isSelected() == true){
                resultado +="apto";
            }
            if(nao_apto.isSelected() == true){
                resultado +="nao apto";
            }
            ava.setExames(exames);
            ava.setCondicao(resultado);
            new AvaliacaoDao().alterar(ava);
            Msg.informacao(this, "salvo com sucesso");
        }
    }*/
    public void salvar_treinamentos() {
        if (isAlterar() == true) {
            preenchecampo3(lista_ver.get(0));
        } else {

        }
    }

    public void salvar_sup() {
        if (isAlterar() == true) {

            preenchecampo4(lista_ver.get(0));
        } else {

        }
    }

    public void salvar_gen() {
        if (isAlterar() == true) {

            preenchecampo5(lista_ver.get(0));
        } else {

        }
    }

    public void salvar_rh() {
        if (isAlterar() == true) {

            preenchecampo6(lista_ver.get(0));
        } else {
        }
    }

    public void buscar_colaborador(String texto) {
        try {
            lista = new AvaliacaoDao().getcolaborador(texto);
            System.out.println(lista.size());
            nome.setText(lista.get(0).getNome());
            admissao.setText(format.format(lista.get(0).getDataAdm()));
            cargo_atual.setText(lista.get(0).getFuncaoId().getFuncao());

            lista_ver = new AvaliacaoDao().getavaliaca(lista.get(0).getId());
            System.out.println(lista_ver.size());
            if(lista_ver.size() > 0){
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
            }else{
                setAlterar(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro " + e.getMessage());
        }
    }

    public void preenchecampos(Avaliacao av) {
        // AVALIAÇÃO
        ava = av;
        registro.setText(av.getIdColaborador().getRegistro());
        nome.setText(av.getIdColaborador().getNome());
        cargo_atual.setText(av.getIdCargoAtual().getFuncao());
        admissao.setText(format.format(av.getIdColaborador().getDataAdm()));
        if (lista_ver.size() > 0) {
            tempo_atual.setText(av.getTempoAtual());
            salario_atual.setText(av.getSalarioAtual().toString());
            salario_proposto.setText(av.getSalarioProposto().toString());
            cargo_proposto.setSelectedItem(av.getIdCargo().getFuncao());
            data_avaliacao.setDate(av.getDataAvaliacao());
        }
        // EXAME

    }

    public void preenchecampo2(Avaliacao av) {

    }

    public void preenchecampo3(Avaliacao av) {

    }

    public void preenchecampo4(Avaliacao av) {

    }

    public void preenchecampo5(Avaliacao av) {

    }

    public void preenchecampo6(Avaliacao av) {

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
        jLabel7 = new javax.swing.JLabel();
        tempo_atual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        salario_atual = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        cargo_proposto = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        data_avaliacao = new org.jdesktop.swingx.JXDatePicker();
        salario_proposto = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("AVALIAÇÃO");
        jPanel1.add(jLabel8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("REGISTRO:");

        registro.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setText("NOME:");

        nome.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setText("ADMISSÃO:");

        admissao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setText("CARGO ATUAL:");

        cargo_atual.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel7.setText("TEMPO NO CARGO");

        tempo_atual.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel9.setText("SALARIO ATUAL:");

        salario_atual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        salario_atual.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel10.setText("CARGO PROPOSTO:");

        cargo_proposto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel11.setText("SALARIO PROPOSTO:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel12.setText("DATA AVALIAÇÃO:");

        data_avaliacao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        salario_proposto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        salario_proposto.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2)
                        .addComponent(cargo_atual, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempo_atual))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(salario_atual))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cargo_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addGap(2, 2, 2)))
                        .addComponent(salario_proposto, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(data_avaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(admissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargo_atual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(tempo_atual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(salario_atual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(cargo_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salario_proposto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(data_avaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setText("SALVAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setText("SAIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(541, 302));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscar_colaborador(registro.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvar_avaliacao();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(adicao_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adicao_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adicao_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adicao_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                adicao_colaborador dialog = new adicao_colaborador(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel admissao;
    private javax.swing.JLabel cargo_atual;
    private javax.swing.JComboBox<String> cargo_proposto;
    private org.jdesktop.swingx.JXDatePicker data_avaliacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel nome;
    private javax.swing.JTextField registro;
    private javax.swing.JFormattedTextField salario_atual;
    private javax.swing.JFormattedTextField salario_proposto;
    private javax.swing.JTextField tempo_atual;
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
