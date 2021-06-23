/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.profissiografia;

import bac.com.br.hibernate.modulo.treinamento.*;
import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.FuncaoDao;
import bac.com.br.hibernate.Dao.HistoricoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.utils.Get_set_tabela_funcoes;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuário
 */
public class Inserir_colaboradores extends javax.swing.JDialog {

    private Listar_colaborador pai;
    private Cadastrar_treinamento tio;
    protected List<Get_set_tabela_funcoes> lista_tabela = new ArrayList<>();
    List<Get_set_tabela_funcoes> salvar = new ArrayList<>();
    Colaborador col;
    Historico his;
    Descricao des;
    List<Funcao> lista = new ArrayList<>();
    List<Historico> lista_historico = new ArrayList<>();
    private boolean alterar;

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    /**
     * Creates new form Cadastrar_colaboradores
     */
    public Inserir_colaboradores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.iniciar();
    }

    public Inserir_colaboradores(Listar_colaborador parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        this.iniciar();
        col = new Colaborador();
    }
    public Inserir_colaboradores(Cadastrar_treinamento sobrinho, boolean modal) {
        super(sobrinho, modal);
        this.tio = sobrinho;
        initComponents();
        this.iniciar();
        col = new Colaborador();
    }
    private void iniciar(){
       // atualizacombo();
        nome.setDocument(new TextDocument(200));
        registro.setDocument(new TextDocument(50));   
    }

    /*
    private void atualizacombo() {
        cbfuncao.removeAllItems();
        cbfuncao.addItem("<...>");
        lista = new FuncaoDao().getlista("");
        for (Funcao f : lista) {
            cbfuncao.addItem(f.getFuncao());
        }
    }
    */
    // preenchimento da janela
    protected void preenchecampos(Colaborador c) {
        col = c;
        registro.setText(c.getRegistro());
        nome.setText(c.getNome());
        data_nascimento.setDate(c.getDataNasc());
        data_admissao.setDate(c.getDataAdm());
        cbstatus.setSelectedItem(c.getStatus());
        pis.setText(c.getPis());
        sexo.setSelectedItem(c.getSexo());
        ctps.setText(c.getCtps());
        serie.setText(c.getSerie());
        estado.setSelectedItem(c.getEstado());
       // cbfuncao.setSelectedItem(c.getFuncao().getFuncao());
    }

    
    // salvar informações do colaborador
    private Colaborador salvar() {
        col.setRegistro(registro.getText());
        col.setNome(nome.getText());
        col.setDataNasc(data_nascimento.getDate());
        col.setDataAdm(data_admissao.getDate());
        col.setStatus(cbstatus.getSelectedItem().toString());
        col.setPis(pis.getText().toString());
        col.setSexo(sexo.getSelectedItem().toString());
        System.out.println(ctps.getText());
        col.setCtps(ctps.getText());
        col.setSerie(serie.getText());
        col.setEstado(estado.getSelectedItem().toString());
        
        //col.setFuncao(lista.get(cbfuncao.getSelectedIndex() - 1));
        Funcao fun = null;
        if(salvar.size()<1){
            fun = new FuncaoDao().seleciona(lista_historico.get(lista_historico.size() - 1).getIdCargo().getId());
        }else{
        fun = new FuncaoDao().seleciona(salvar.get(salvar.size() - 1).getId_funcao());
        }
        col.setFuncaoId(fun);
        return col;
    }

    // validar preenchimentos dos campos
    private boolean validacampos() {
        boolean va = false;
        String vazio = "campos vazios\n";
        if (nome.getText().equals("")) {
            vazio += "nome\n";
            va = true;
        }
        if (registro.getText().equals("")) {
            vazio += "registro\n";
            va = true;
        }
        if (data_nascimento.getDate() == null) {
            vazio += "nascimento\n";
            va = true;
        }
        if (data_admissao.getDate() == null) {
            vazio += "admissão \n";
            va = true;
        }
        /*
        if (cbfuncao.getSelectedIndex() == 0) {
            vazio += "função\n";
            va = true;
        }
        */
        if (cbstatus.getSelectedIndex() == 0) {
            vazio += "status\n";
            va = true;
        }
        if (va == true) {
            Msg.alerta(this, vazio);
        }
        return va;
    }
    
    protected void excluir(){
        int row = tabela_funcao.getSelectedRow();
        if(row < 0){
            Msg.alerta(this, "selecione um registro da tabela");
        }else{
            if(Msg.confirmar(this, "deseja realmente deletar esta informação?") == false){
                return;
            }else{
                try {
                    new HistoricoDao().excluir(lista_historico.get(row));
                    Msg.informacao(this, "item excluido com sucesso");
                    listar_salvos(col.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao excluir item"+e.getMessage());
                }
            }
        }
    }
    
    // listar as informações ja salvas no histórico do colaborador
    protected void listar_salvos(Long id){
        lista_historico = new ColaboradorDao().cursos(id);
        System.out.println(lista_historico.size());
        for (Historico his : lista_historico) {
            Get_set_tabela_funcoes get = new Get_set_tabela_funcoes();
            get.setInicio(his.getPeriodoInicio());
            get.setFim(his.getPeriodoFim());
            get.setFuncao(his.getIdCargo().getFuncao());
            lista_tabela.add(get);
        }
        carregar_funcoes();
    }
    
    // carregar informações do histórico do colaborador ja salvos e a serem salvas.
    protected void carregar_funcoes(){
        DefaultTableModel model = (DefaultTableModel) tabela_funcao.getModel();
        model.setNumRows(0);
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
        for (Get_set_tabela_funcoes get : lista_tabela) {
            model.addRow(new Object[]{
                get.getInicio() != null?form.format(get.getInicio()):get.getInicio(),
                get.getFim() != null?form.format(get.getFim()):get.getFim(),
                get.getFuncao()
            });
        }
    }
    private void terminar(){
        System.out.println("verificando");
        verificar_salvos();
        inserir_alterar();
        System.out.println("salvando");
        Salvar_no_historico();
        System.out.println("alterando");
        update_funcao_anterior();
    }
    // alterar informação do cargo anterior em que o colaborador atuava anteriormente
    private void update_funcao_anterior(){
        if(salvar.size() >= 1){
            if(lista_historico.size() != 0){
                his = lista_historico.get(lista_historico.size()-1);
                his.setPeriodoFim(salvar.get(0).getInicio());
                new HistoricoDao().update(his);
            }
        }
    }
    // verificar as informações a serem salvas no historico do colaborador
    private void verificar_salvos(){
        try {
            
            for (int i = lista_historico.size(); i < lista_tabela.size(); i++) {
                salvar.add(lista_tabela.get(i));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao verificar salvos"+e.getMessage());
        }
    }
    // realiza a inclusao de novo registro na tabela historico
    private void Salvar_no_historico(){
        
        try {
            for (Get_set_tabela_funcoes sal : salvar) {
               
                Historico h = new Historico();
                h.setPeriodoInicio(sal.getInicio());
                h.setPeriodoFim(sal.getFim());
                Funcao f = new FuncaoDao().seleciona(sal.getId_funcao());
                h.setIdCargo(f);
                des = new DescricaoDao().seleciona(sal.getId_descricao());
                h.setIdDescricao(des);
                h.setIdColaborador(col);
                new HistoricoDao().persist(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao salvar historico"+e.getMessage());
        }
    }
    
    // inserir ou alterar cadastro do colaborador.
    private void inserir_alterar() {
        if (validacampos() == false) {
            if (isAlterar() == true) {
                try {
                    new ColaboradorDao().alterar(salvar());
                    Loggin.arquivo_log("alterado cadastro de um novo colaborador com o id"+col.getId()+
                            " com os itens:"+col.getNome()+";"+col.getRegistro()+";"+col.getDataAdm()+";"+col.getDataNasc()+";"+col.getFuncaoId());
                    Msg.informacao(this, "alterado com sucesso");
                    pai.atualizatabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao alterar as informações \nErro: " + e.getMessage());
                }
            } else {
                try {
                    new ColaboradorDao().inserir(salvar());
                    //new HistoricoDao().persist(salvar_historico());
                    Loggin.arquivo_log("criado cadastro de um novo colaborador com o id"+col.getId()+
                            " com os itens:"+col.getNome()+";"+col.getRegistro()+";"+col.getDataAdm()+";"+col.getDataNasc()+";"+col.getFuncaoId());
                    Msg.informacao(this, "salvo com sucesso");
                       if(Msg.confirmar(this, "deseja adicionar item ao histórico de cargos ?") == true){
                            Adicionar_historico da = new Adicionar_historico(this, true);
                            da.seleciona(col);
                         //   da.listar(col.getId());
                            da.setVisible(true);
                       }
                    if(pai != null){
                        pai.atualizatabela();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao inserir colaborador \nErro: " + e.getMessage());
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        registro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        data_nascimento = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        data_admissao = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        cbstatus = new componentes.UJComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pis = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        ctps = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        serie = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_funcao = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 40, 5));

        jPanel1.setLayout(new java.awt.GridLayout(5, 2, 5, 5));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO:");
        jPanel1.add(jLabel1);

        registro.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(registro);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NOME:");
        jPanel1.add(jLabel2);

        nome.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(nome);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATA DE NASCIMENTO:");
        jPanel1.add(jLabel3);

        data_nascimento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(data_nascimento);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DATA DE ADMISSAO:");
        jPanel1.add(jLabel4);

        data_admissao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(data_admissao);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("STATUS:");
        jPanel1.add(jLabel5);

        cbstatus.setEditable(true);
        cbstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<...>", "ATIVO", "INATIVO" }));
        cbstatus.setAutocompletar(true);
        cbstatus.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel1.add(cbstatus);

        jPanel2.add(jPanel1);

        jPanel6.setLayout(new java.awt.GridLayout(5, 2, 5, 5));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NIT/PIS:");
        jPanel6.add(jLabel6);

        pis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        pis.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel6.add(pis);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SEXO:");
        jPanel6.add(jLabel7);

        sexo.setEditable(true);
        sexo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<...>", "M", "F" }));
        sexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexoActionPerformed(evt);
            }
        });
        jPanel6.add(sexo);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CTPS:");
        jPanel6.add(jLabel8);

        ctps.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        ctps.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel6.add(ctps);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SERIE:");
        jPanel6.add(jLabel9);

        serie.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        serie.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel6.add(serie);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ESTADO:");
        jPanel6.add(jLabel10);

        estado.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<..>", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG ", "MS", "MT", "PA", "PB ", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO" }));
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });
        jPanel6.add(estado);

        jPanel2.add(jPanel6);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

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

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        tabela_funcao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabela_funcao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INICIO DAS ATIVIDADES", "FINAL DAS ATIVIDADES", "CARGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela_funcao);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setText("ADICIONAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setText("REMOVER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);

        jPanel5.add(jPanel7);

        jPanel4.add(jPanel5, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(566, 395));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     terminar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            Adicionar_historico add = new Adicionar_historico(this, true);
            add.seleciona(col);
            add.setVisible(true);
            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void sexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexoActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       excluir();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Inserir_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inserir_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inserir_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inserir_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Inserir_colaboradores dialog = new Inserir_colaboradores(new javax.swing.JFrame(), true);
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
    private componentes.UJComboBox cbstatus;
    private javax.swing.JFormattedTextField ctps;
    private org.jdesktop.swingx.JXDatePicker data_admissao;
    private org.jdesktop.swingx.JXDatePicker data_nascimento;
    private javax.swing.JComboBox estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JFormattedTextField pis;
    private javax.swing.JTextField registro;
    private javax.swing.JFormattedTextField serie;
    private javax.swing.JComboBox sexo;
    private javax.swing.JTable tabela_funcao;
    // End of variables declaration//GEN-END:variables
}
