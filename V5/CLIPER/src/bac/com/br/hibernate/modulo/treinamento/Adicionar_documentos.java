/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Adicionar_documentos extends javax.swing.JDialog {
    List<Setor> lista_setor = new ArrayList<Setor>();
    List<Documento> lista_documento = new ArrayList<Documento>();
    List<String> lista_string = new ArrayList<String>();
    List<String> lista_tabela = new ArrayList<String>();
    Descricao desc;
    /**
     * Creates new form Adicionar_documentos
     */
    public Adicionar_documentos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        combo_setor();
        Utils.maximizar(this);
        desc = new Descricao();
    }
    private void iniciar(){
        tabeladocumentos.getColumn(1).setMaxWidth(0);
        tabeladocumentos.getColumn(1).setMinWidth(0);
        
    }    
    
    private void combo_setor(){
        setores.removeAllItems();
        setores.addItem("<...>");
        try {
            lista_setor = new DescricaoDao().combo_setor();
            for (Setor s : lista_setor) {
                setores.addItem(s.getNomeSetor());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro ao carregar setores \n"+e.getMessage());
        }
    }
    private void trazdocumentos(){
        try {
            lista_documento = new DescricaoDao().traz_documentos(setores.getSelectedItem().toString());
            DefaultTableModel model = (DefaultTableModel) tabeladocumentos.getModel();
            model.setNumRows(0);
            for (Documento d : lista_documento) {
                model.addRow(new Object[]{
                    d.getId(),
                    d.getTitulo(),
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao carregar tabela\n"+e.getMessage());
        }
    }
    private void remove_documento() {
        int row = listardocumentos.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            String remove = listardocumentos.getValueAt(row, 0).toString();
            lista_string.remove(remove);
            lista_tabela.remove(remove);
            DefaultTableModel model = (DefaultTableModel) listardocumentos.getModel();
            model.setNumRows(0);
            for (String s : lista_tabela) {
                model.addRow(new Object[]{
                    s
                });
            }
        }
    }
    private void transfere_documento(){
        String contem = "";
        int row[] = tabeladocumentos.getSelectedRows();
        if(row.length <=0){
            Msg.alerta(this, "selecione um registro");
        }else{
            for(int i = 0; i< row.length;i++){
                String um = tabeladocumentos.getValueAt(row[i],0).toString();
            String dois = tabeladocumentos.getValueAt(row[i], 1).toString();
            System.out.println(dois);
            
                    lista_string.add(um);
                    lista_tabela.add(dois);
                    DefaultTableModel model =(DefaultTableModel) listardocumentos.getModel();
                    model.setNumRows(0);
                        for(String s : lista_tabela){
                            model.addRow(new Object[]{
                                s
                            });
                        }
            }
                
        }
    }
    List<String> docs;
    protected void preenche(Descricao des){
        desc = des;
        /*
        docs = new DescricaoDao().documentos_obrigatorios(des.getId());
        System.out.println(docs.size());
        for(int i=0; i<docs.size();i++){
            int d = new DescricaoDao().adiciona_documento_nome(docs.get(i));
            Documento doc = new DescricaoDao().adiciona_documento(Long.valueOf(d));
            lista_documento.add(doc);
        }*/
    }
    
    private Descricao salvar(){
        try {
            lista_documento.clear();
        if(desc.getDocumentoList().size() > 0){
            for(int j = 0; j < desc.getDocumentoList().size();j++){
                lista_documento.add(desc.getDocumentoList().get(j));
            }
        }
        for(int i =0; i< lista_string.size();i++){
            long ex = Long.parseLong(lista_string.get(i));
            Documento d = new DescricaoDao().adiciona_documento(ex);
            lista_documento.add(d);
        }
        desc.setDocumentoList(lista_documento);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao salvar registro \n"+e.getMessage());
        }
        return desc;
    }
    private void concluir(){
        try {
            new DescricaoDao().inserir(salvar());
            Msg.informacao(this, "salvo com sucesso");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao concluir \n"+e.getMessage());
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeladocumentos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listardocumentos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        setores = new componentes.UJComboBox();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jPanel3.setLayout(new java.awt.BorderLayout());

        tabeladocumentos.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tabeladocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabeladocumentos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabeladocumentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabeladocumentos);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/add.png"))); // NOI18N
        jButton2.setText("ADICIONAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        listardocumentos.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        listardocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TITULO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listardocumentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(listardocumentos);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton5.setText("REMOVER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5);

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("SETOR:");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setores.setEditable(true);
        setores.setAutocompletar(true);
        setores.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setores, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(setores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton6.setText("CONCLUIR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton7.setText("CANCELAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7);

        getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(419, 365));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = setores.getSelectedIndex();
        if(row <= 0){
            Msg.alerta(this, "selecione um registro");
        }else{
            trazdocumentos();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        transfere_documento();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        remove_documento();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        concluir();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Adicionar_documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Adicionar_documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Adicionar_documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Adicionar_documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Adicionar_documentos dialog = new Adicionar_documentos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JTable listardocumentos;
    private componentes.UJComboBox setores;
    private javax.swing.JTable tabeladocumentos;
    // End of variables declaration//GEN-END:variables
}
