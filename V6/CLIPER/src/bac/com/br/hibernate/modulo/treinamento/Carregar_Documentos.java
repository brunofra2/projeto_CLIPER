/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.relatorios.Relatorio_documentos;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Usuário
 */
public class Carregar_Documentos extends javax.swing.JDialog {

    private Tela_principal pai;
    List<Documento> lista = new ArrayList<Documento>();
    Documento doc;

    /**
     * Creates new form Carregar_Ducumentos
     */
    public Carregar_Documentos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utils.maximizar(this);
        atualizatabela("");
        atualizacombo();
    }

    public Carregar_Documentos(Tela_principal parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
        doc = new Documento();
        atualizatabela("");
        atualizacombo();
    }

    protected void atualizatabela(String s) {
        try {
            lista = new DocumentoDao().getlista(s);
            DefaultTableModel model = (DefaultTableModel) TABELA.getModel();
            model.setNumRows(0);
            for (Documento d : lista) {
                model.addRow(new Object[]{
                    d.getTitulo(),
                    d.getTipo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro ao atualizar a tabela!" + e.getMessage());
        }
    }

    private void desativar() {
        int row = TABELA.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            doc = lista.get(row);
            doc.setModificacao("INATIVO");
            if (Msg.confirmar(this, "deseja realmente desativar esse colaborador") == true) {
                try {
                    new DocumentoDao().alterar(doc);
                Loggin.arquivo_log("desativado cadastro de um documento com o id"+lista.get(row));
                    Msg.informacao(this, "colaborador desativado com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao desativar colaborador");
                }
            } else {
                return;
            }
        }
    }

    private void ativar() {
        int row = TABELA.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            doc = lista.get(row);
            doc.setModificacao("ATIVO");
            if (Msg.confirmar(this, "deseja realmente reativar esse colaborador") == true) {
                try {
                    new DocumentoDao().alterar(doc);
                Loggin.arquivo_log("ativado cadastro de um documento com o id"+lista.get(row));
                    Msg.informacao(this, "colaborador reativado com sucesso");
                    atualizatabela("");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao reativar colaborador");
                }
            } else {
                return;
            }
        }
    }
    String min;
    String max;

    private void atualizacombo() {
        cbano.removeAllItems();
        List<String> lista1 = new TreinamentoDao().min2();
        min = String.valueOf(lista1.get(0));
        List<String> lista2 = new TreinamentoDao().max2();
        max = String.valueOf(lista2.get(0));
        int a = Integer.parseInt(min);
        int b = Integer.parseInt(max);
        cbano.addItem("<...>");
        for (int i = a; i <= b; i++) {
            cbano.addItem(i);
            System.out.println(i);
        }
    }

    protected void atualizard() {
        try {
            int row = TABELA.getSelectedRow();
            if(row < 0){
                Msg.alerta(this, "selecione o item da tabela");
            }else{
                String ano = cbano.getSelectedItem().toString();
            if(ano.equals("<...>")){
                Msg.alerta(this, "selecione o periodo desejado");
            }else{
                List<String> lista1 = new DocumentoDao().colaboradores(lista.get(row).getId(), ano);

            DefaultTableModel model = (DefaultTableModel) tabelacol.getModel();
            model.setNumRows(0);
            for (int i = 0; i < lista1.size(); i++) {
                String s = String.valueOf(lista1.get(i));
                model.addRow(new Object[]{
                    s,});
            }
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + Utils.getErro(e));
        }
    }
    
    protected void imprimir() {
        try {
            int row = TABELA.getSelectedRow();
            if(row < 0){
                Msg.alerta(this, "selecione o item da tabela");
            }else{
                String ano = cbano.getSelectedItem().toString();
            if(ano.equals("<...>")){
                Msg.alerta(this, "selecione o periodo desejado");
            }else{
                new Relatorio_documentos(lista.get(row).getId(), cbano.getSelectedItem().toString(),Utils.Sistema_operacional());
                System.out.println("\n"+lista.get(row).getId()+""+ cbano.getSelectedItem().toString());
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + Utils.getErro(e));
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
        txpesquisa = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelacol = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        cbano = new componentes.UJComboBox();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABELA = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("PESQUISAR:");

        txpesquisa.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        txpesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txpesquisaKeyReleased(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton5.setText("BUSCAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton5KeyReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atualizar.png"))); // NOI18N
        jButton6.setText("ATUALIZAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txpesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        tabelacol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacol.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelacol);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        cbano.setEditable(true);
        cbano.setAutocompletar(true);
        cbano.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel6.add(cbano);

        jButton9.setText("visualizar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton9);

        jButton10.setText("imprimir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton10);

        jPanel5.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel5, java.awt.BorderLayout.WEST);

        TABELA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TITULO", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABELA.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TABELA);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_add.png"))); // NOI18N
        jButton1.setText("INSERIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_edit.png"))); // NOI18N
        jButton2.setText("ALTERAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/status_online.png"))); // NOI18N
        jButton7.setText("ATIVAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/status_offline.png"))); // NOI18N
        jButton8.setText("DESATIVAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_delete.png"))); // NOI18N
        jButton3.setText("EXCLUIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton4.setText("SAIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Cadastrar_documento c = new Cadastrar_documento(this, true);
        c.setAlterar(false);
        c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (txpesquisa.getText().equals("")) {
            Msg.alerta(this, "digite um parametro para a pesquisa");
        } else {
            atualizatabela(txpesquisa.getText());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        atualizatabela("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = TABELA.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            Cadastrar_documento c = new Cadastrar_documento(this, true);
            c.setAlterar(true);
            c.preenchecampos(lista.get(row));
            c.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = TABELA.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            if (Msg.confirmar(this, "deseja realmente excluir este registro") == false) {
                return;
            } else {
                try {
                    new DocumentoDao().excluir(lista.get(row));
                Loggin.arquivo_log("excluido cadastro de um documento com o id"+lista.get(row));
                    atualizatabela("");
                    Msg.informacao(this, "registro excluido com sucesso");
                } catch (Exception e) {
                    if (e.getMessage().contains("Error while committing the transaction")) {
                        Msg.alerta(this, "Este registro não pode ser excluido\n"
                                + "ele esta vinculado ha outro modulo do sistema");
                    } else {
                        e.printStackTrace();
                        Msg.erro(this, "Erro ao excluir registro\nErro: " + e.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        desativar();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ativar();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        atualizard();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       imprimir();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (txpesquisa.getText().equals("")) {
            Msg.alerta(this, "digite um parametro para a pesquisa");
        } else {
            atualizatabela(txpesquisa.getText());
        }
    }//GEN-LAST:event_formKeyReleased

    private void jButton5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyReleased
        if (txpesquisa.getText().equals("")) {
            Msg.alerta(this, "digite um parametro para a pesquisa");
        } else {
            atualizatabela(txpesquisa.getText());
        }
    }//GEN-LAST:event_jButton5KeyReleased

    private void txpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpesquisaKeyReleased
      if (txpesquisa.getText().equals("")) {
            Msg.alerta(this, "digite um parametro para a pesquisa");
        } else {
            atualizatabela(txpesquisa.getText());
        }
    }//GEN-LAST:event_txpesquisaKeyReleased

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
            java.util.logging.Logger.getLogger(Carregar_Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carregar_Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carregar_Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carregar_Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Carregar_Documentos dialog = new Carregar_Documentos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TABELA;
    private componentes.UJComboBox cbano;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelacol;
    private javax.swing.JTextField txpesquisa;
    // End of variables declaration//GEN-END:variables
}

class MycomboboxEditor extends DefaultCellEditor {

    public MycomboboxEditor(String[] items) {
        super(new JComboBox(items));
    }
}

class Mycombobox extends JComboBox implements TableCellRenderer {

    public Mycombobox(String[] itens) {
        super(itens);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelectedItem(value);
        return this;
    }
}
