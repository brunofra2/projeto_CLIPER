/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.AtividadeDao;
import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.FuncaoDao;
import bac.com.br.hibernate.entidade.Atividades;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.utils.Get_set_tabela_funcoes;
import bac.com.br.hibernate.utils.Msg;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Adicionar_periodos extends javax.swing.JDialog {
     
    private Carregar_colaboradores pai;
    private Cadastrar_colaboradores irmao;
    Historico his;
    Colaborador col;
    Descricao des;
    
    List<Funcao> lista = new ArrayList<>();
    List<Historico> lista_historico = new ArrayList<>();
    List<Atividades> lista_atividades = new ArrayList<>();
    /**
     * Creates new form Adicionar_periodos
     */
    public Adicionar_periodos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        atualizacombo();
//        listar(col.getId());
        initComponents();
    }
    
    public Adicionar_periodos(Carregar_colaboradores parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        atualizacombo();
    }
    
    
    public Adicionar_periodos(Cadastrar_colaboradores parent, boolean modal) {
        super(parent, modal);
        this.irmao = parent;
        initComponents();
        atualizacombo();
    }
    
    
    protected void seleciona(Colaborador cola){
        this.col = cola;
    }
    private void atualizacombo() {
        cbfuncao.removeAllItems();
        cbfuncao.addItem("<...>");
        lista = new FuncaoDao().getlista("");
        for (Funcao f : lista) {
            cbfuncao.addItem(f.getFuncao());
        }
    }

   /*
     protected void listar(Long id){
        lista_historico = new ColaboradorDao().cursos(id);
        DefaultTableModel model = (DefaultTableModel) Tabela_funcoes.getModel();
        model.setNumRows(0);
        
        for (Historico his : lista_historico) {
            model.addRow(new Object[]{
                his.getIdCargo().getFuncao(),
                his.getPeriodoInicio(),
                his.getPeriodoFim()
            });
        }
            
           
    }
    */
    private void verificar_selecao(){
        if(Cargo_atual.isSelected() == true){
            data_fim.setVisible(false);
        }else{
            data_fim.setVisible(true);
        }
    }
    
    private boolean preencher(){
        boolean v = false;
        String msg = "De atenção aos seguintes itens: \n";
        if(Cargo_atual.isSelected() == false){
            if(data_fim.getDate().before(data_inicio.getDate())){
                v = true;
                msg+="data de alteração não pode ser anterior a data de inicio";
            }
        }
        if(cbfuncao.getSelectedIndex() <=0){
            v = true;
            msg+="selecione a função ha ser adiciondada";
        }
        if(v == true){
            Msg.alerta(this, msg);
        }
        return v;
    }
    
    private void adicionar_funcao(){
        try {
            if(preencher() == false){
                Get_set_tabela_funcoes get = new Get_set_tabela_funcoes();
                get.setInicio(data_inicio.getDate());
                if(Cargo_atual.isSelected() == false){
                    get.setFim(data_fim.getDate());
                }
                //get.setFim(data_fim.getDate());
                get.setFuncao(cbfuncao.getSelectedItem().toString());
                get.setId_funcao(lista.get(cbfuncao.getSelectedIndex()-1).getId());
                System.out.println(lista.get(cbfuncao.getSelectedIndex()-1).getId());
                Long id_des = new DescricaoDao().salvar_historico(lista.get(cbfuncao.getSelectedIndex()-1).getId());
                des = new DescricaoDao().seleciona(id_des);
                get.setId_descricao(des.getId());
                System.out.println("id_salv"+get.getId_funcao());
                irmao.lista_tabela.add(get);
                irmao.carregar_funcoes();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao adicionar funcao"+e.getMessage());
        }
    }
    private void carregar_atividade(String f){
        try {
            
        lista_atividades = new AtividadeDao().get(f);
        atividades.setText(lista_atividades.get(0).getDescricao());
        SITUACAO.setText(lista_atividades.get(0).getIdCargo().getCondicao());
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "função não tem atividade cadastrada");
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

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        data_inicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        data_fim = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        Cargo_atual = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbfuncao = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        SITUACAO = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        atividades = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setLayout(new java.awt.GridLayout(4, 0));

        jLabel2.setText("Data inicio das atividades");
        jPanel4.add(jLabel2);
        jPanel4.add(data_inicio);

        jLabel3.setText("Data fim das atividades");
        jPanel4.add(jLabel3);
        jPanel4.add(data_fim);

        jLabel5.setText("Cargo Atual ?");
        jPanel4.add(jLabel5);

        Cargo_atual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cargo_atualActionPerformed(evt);
            }
        });
        jPanel4.add(Cargo_atual);

        getContentPane().add(jPanel4, java.awt.BorderLayout.NORTH);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setText("SALVAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jLabel4.setText("Cargo");

        cbfuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbfuncao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbfuncaoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbfuncaoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbfuncaoMouseReleased(evt);
            }
        });
        cbfuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfuncaoActionPerformed(evt);
            }
        });

        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbfuncao, 0, 297, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbfuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton2))
                .addGap(2, 2, 2))
        );

        jPanel2.add(jPanel7);

        jLabel6.setText("SITUAÇÃO:");
        jPanel6.add(jLabel6);

        SITUACAO.setText("jLabel7");
        jPanel6.add(SITUACAO);

        jPanel2.add(jPanel6);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("ATIVIDADE");
        jPanel5.add(jLabel1, java.awt.BorderLayout.NORTH);

        atividades.setColumns(20);
        atividades.setRows(5);
        jScrollPane1.setViewportView(atividades);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adicionar_funcao();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Cargo_atualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cargo_atualActionPerformed
    verificar_selecao();
    }//GEN-LAST:event_Cargo_atualActionPerformed

    private void cbfuncaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbfuncaoMouseClicked
       
    }//GEN-LAST:event_cbfuncaoMouseClicked

    private void cbfuncaoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbfuncaoMouseReleased
 
    }//GEN-LAST:event_cbfuncaoMouseReleased

    private void cbfuncaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbfuncaoMousePressed
         
    }//GEN-LAST:event_cbfuncaoMousePressed

    private void cbfuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfuncaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbfuncaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         System.out.println(String.valueOf(cbfuncao.getSelectedIndex()-1));
        carregar_atividade(String.valueOf(lista.get(cbfuncao.getSelectedIndex()-1).getId()));
        System.out.println(lista.get(cbfuncao.getSelectedIndex()-1).getId());
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
            java.util.logging.Logger.getLogger(Adicionar_periodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Adicionar_periodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Adicionar_periodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Adicionar_periodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Adicionar_periodos dialog = new Adicionar_periodos(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox Cargo_atual;
    private javax.swing.JLabel SITUACAO;
    private javax.swing.JTextArea atividades;
    private javax.swing.JComboBox<String> cbfuncao;
    private org.jdesktop.swingx.JXDatePicker data_fim;
    private org.jdesktop.swingx.JXDatePicker data_inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
