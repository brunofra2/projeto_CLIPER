/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.ItensDao;
import bac.com.br.hibernate.Dao.PeriodoDao;
import bac.com.br.hibernate.Dao.PgrDao;
import bac.com.br.hibernate.entidade.Itens;
import bac.com.br.hibernate.entidade.Pgr;
import bac.com.br.hibernate.entidade.Periodo;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Adicionar_itens extends javax.swing.JDialog {

    private pesquisar_pgr pai;

    List<Periodo> lista_colaborador;
    List<String> adicionar_tabela_colaborador = new ArrayList<String>();
    List<String> inserir_colaborador = new ArrayList<String>();

    Itens i;
    Pgr p;
    private boolean alterar;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    /**
     * Creates new form Adicionar_itens
     */
    public Adicionar_itens(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        i = new Itens();
        iniciar();
    }

    public Adicionar_itens(pesquisar_pgr parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        i = new Itens();
        iniciar();
    }

    private void iniciar() {
        descrica.setDocument(new TextDocument(100));
        trazmeseses();
    }

    private boolean verifica() {
        boolean f = false;
        String s = "campo vazio \n";
        if (descrica.getText().equals("")) {
            f = true;
            s += "ano";
        }
        if (f == true) {
            Msg.alerta(this, s);
        }
        return f;
    }

    protected void seleciona(Pgr pa) {
        this.p = pa;
    }

    private Itens salvar() {
        i.setDescricao(descrica.getText());
        //ABAIXO ESTÃO AS CHAVES ESTRAGEIRAS
        //CHAVE ESTRANGEIRA COLABORADORES
        lista_colaborador.clear();
        for (int i = 0; i < inserir_colaborador.size(); i++) {
            long ex = Long.parseLong(inserir_colaborador.get(i));
            Periodo c = new PeriodoDao().adiciona_meses(ex);
            lista_colaborador.add(c);
        }
        i.setPeriodoList(lista_colaborador);
        i.setPgrId(p);
        return i;
    }

    private void concluir() {
        if (isAlterar() == false) {
            if (verifica() == false) {
                try {
                    new ItensDao().inserir(salvar());
                    Loggin.arquivo_log("pgr criado com o codigo"+i.getId());
                    Msg.informacao(this, "registro salvo com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao inserir registro \n" + e.getMessage());
                }
            }
        } else {
            if (verifica() == false) {
                try {
                    new ItensDao().alterar(salvar());
                    Msg.informacao(this, "registro alterado com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao alterar registro \n" + e.getMessage());
                }
            }
        }
        pai.atualizaritens("");
        dispose();
    }

    private void trazmeseses() {
        try {
            lista_colaborador = new PeriodoDao().getcolaborador();
            DefaultTableModel model = (DefaultTableModel) tabelameses.getModel();
            model.setNumRows(0);
            for (Periodo c : lista_colaborador) {
                model.addRow(new Object[]{
                    c.getId(),
                    c.getPeriodo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao trazer os colaboradores \n" + e.getMessage());
        }
    }

    private void remove_meses() {
        int row = listarmeses.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            String remove = listarmeses.getValueAt(row, 0).toString();
            inserir_colaborador.remove(remove);
            adicionar_tabela_colaborador.remove(remove);
            DefaultTableModel model = (DefaultTableModel) listarmeses.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_colaborador) {
                model.addRow(new Object[]{
                    s
                });
            }
        }
    }

    private void adicionar_meses() {
        int row = tabelameses.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            String transfere = tabelameses.getValueAt(row, 1).toString();
            String transfere2 = tabelameses.getValueAt(row, 0).toString();
            inserir_colaborador.add(transfere2);
            adicionar_tabela_colaborador.add(transfere);
            DefaultTableModel model = (DefaultTableModel) listarmeses.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_colaborador) {
                model.addRow(new Object[]{
                    s
                });
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
        descrica = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelameses = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listarmeses = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.GridLayout(3, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("Descricao");
        jPanel1.add(jLabel1);

        descrica.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(descrica);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton1.setText("SALVAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setLayout(new java.awt.BorderLayout());

        tabelameses.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabelameses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelameses.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelameses.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelameses);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/add.png"))); // NOI18N
        jButton3.setText("adicionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);

        jPanel4.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        listarmeses.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        listarmeses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listarmeses.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listarmeses.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(listarmeses);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton4.setText("remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);

        jPanel5.add(jPanel7, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel5);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(615, 463));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        concluir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = tabelameses.getSelectedRow();

        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            adicionar_meses();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = listarmeses.getSelectedRow();

        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            remove_meses();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Adicionar_itens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Adicionar_itens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Adicionar_itens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Adicionar_itens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Adicionar_itens dialog = new Adicionar_itens(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField descrica;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listarmeses;
    private javax.swing.JTable tabelameses;
    // End of variables declaration//GEN-END:variables
}
