/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.relatorios.Relatorio_ata;
import bac.com.br.hibernate.relatorios.Relatorio_termo;
import bac.com.br.hibernate.utils.Daos_Nativos;
import bac.com.br.hibernate.utils.Get_setor;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Rel_desc_car;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Carregar_treinamento extends javax.swing.JDialog {

    private Tela_principal pai;
    List<Treinamento> lista;
    Treinamento tre;
    List<Colaborador> lista_colaboradores;
    private String pesquisa;

    /**
     * Creates new form Contrle_treinamento
     */
    public Carregar_treinamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.atualizar();
        Utils.maximizar(this);
        tre = new Treinamento();
    }

    public Carregar_treinamento(Tela_principal parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
        this.atualizar();
        tre = new Treinamento();
    }

    protected void atualizar() {
        try {
            lista = new TreinamentoDao().atualizar();
            
            DefaultTableModel model = (DefaultTableModel) TABELA.getModel();
            model.setNumRows(0);
            SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
            for (Treinamento t : lista) {
                model.addRow(new Object[]{
                    t.getId(),
                    a.format(t.getDataTreinamento()),
                    t.getTipo(),
                    t.getHorarioInicio(),
                    t.getHorarioFinal(),
                    a.format(t.getProxTreinamento()),
                    t.getPastaId().getNumero(),
                });
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar \n" + e.getMessage());
        }
    }

    protected void pesquisar(String s) {
        try {
            lista = new TreinamentoDao().getlista(s);
            DefaultTableModel model = (DefaultTableModel) TABELA.getModel();
            model.setNumRows(0);
            SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
            for (Treinamento t : lista) {
                model.addRow(new Object[]{
                    t.getId(),
                    a.format(t.getDataTreinamento()),
                    t.getTipo(),
                    t.getHorarioInicio(),
                    t.getHorarioFinal(),
                    a.format(t.getProxTreinamento()),
                    t.getPastaId().getNumero()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar \n" + e.getMessage());
        }
    }

    public Treinamento prorrogar() {
        tre.setStatus("PRORROGADO");
        return tre;
    }

    private void pesquisa() {
        if ((pesquisa != "participante") && (pesquisa != "tipo")) {
            Msg.alerta(this, "selecione o tipo de busca a ser realizada");
        } else {
            if (txpesquisa.getText().equals("")) {
                Msg.alerta(this, "preencha o campo para realizar a busca");
            } else {
                if (this.pesquisa.equals("participante")) {
                    System.err.println("\n entrou aqui");
                    try {
                        List<BigInteger> lista1 = new TreinamentoDao().participanteid(txpesquisa.getText());
                        List<Date> lista2 = new TreinamentoDao().participantedata(txpesquisa.getText());
                        List<String> lista3 = new TreinamentoDao().participantetipo(txpesquisa.getText());
                        List<String> lista4 = new TreinamentoDao().participanteinicio(txpesquisa.getText());
                        List<String> lista5 = new TreinamentoDao().participantefinal(txpesquisa.getText());
                        List<Date> lista6 = new TreinamentoDao().participantevenc(txpesquisa.getText());
                        List<String> lista7 = new TreinamentoDao().participantepasta(txpesquisa.getText());
                        DefaultTableModel model = (DefaultTableModel) TABELA.getModel();
                        model.setNumRows(0);
                        SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
                        for (int i = 0; i < lista1.size(); i++) {
                            model.addRow(new Object[]{
                                lista1.get(i),
                                a.format(lista2.get(i)),
                                lista3.get(i),
                                lista4.get(i),
                                lista5.get(i),
                                a.format(lista6.get(i)),
                                lista7.get(i)
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Msg.erro(this, "erro ao atualizar a tabela\nErro:" + Utils.getErro(e));
                    }
                } else {
                    try {
                        System.err.println("\n entrou aqui tipo");
                        lista = new TreinamentoDao().getlista(txpesquisa.getText());
                        DefaultTableModel model = (DefaultTableModel) TABELA.getModel();
                        model.setNumRows(0);
                        SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
                        for (Treinamento t : lista) {
                            model.addRow(new Object[]{
                                t.getId(),
                                a.format(t.getDataTreinamento()),
                                t.getTipo(),
                                t.getHorarioInicio(),
                                t.getHorarioFinal(),
                                a.format(t.getProxTreinamento())
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
                    }
                }
            }
        }
    }

    protected void atualizarc() {
        try {
            int row = TABELA.getSelectedRow();
            List<String> lista1 = new TreinamentoDao().registro(Long.parseLong(TABELA.getValueAt(row, 0).toString()));
            List<String> lista2 = new TreinamentoDao().nome(Long.parseLong(TABELA.getValueAt(row, 0).toString()));

            DefaultTableModel model = (DefaultTableModel) tabelacolabores.getModel();
            model.setNumRows(0);
            for (int i = 0; i < lista1.size(); i++) {
                String s = String.valueOf(lista1.get(i));
                String b = String.valueOf(lista2.get(i));
                model.addRow(new Object[]{
                    s,
                    b
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + Utils.getErro(e));
        }
    }

    protected void atualizard() {
        try {
            int row = TABELA.getSelectedRow();
            List<String> lista1 = new TreinamentoDao().titulo(Long.parseLong(TABELA.getValueAt(row, 0).toString()));
            List<String> lista2 = new TreinamentoDao().tipo(Long.parseLong(TABELA.getValueAt(row, 0).toString()));

            DefaultTableModel model = (DefaultTableModel) tabeladocumentos.getModel();
            model.setNumRows(0);
            for (int i = 0; i < lista1.size(); i++) {
                String s = String.valueOf(lista1.get(i));
                String b = String.valueOf(lista2.get(i));
                model.addRow(new Object[]{
                    s,
                    b
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + Utils.getErro(e));
        }
    }
    
    
    /** PROCESSO DE ALTERAÇÃO **/
    
    public void alterar(){
        try {
            int row = TABELA.getSelectedRow();
            
            if(row < 0){
                Msg.alerta(this, "selecione um registro");
            }else{
                Cadastrar_treinamento cad = new Cadastrar_treinamento(this, true);
                cad.setAlterar(true);
               // cad.excluir(Integer.parseInt(lista.get(row).getId().toString()));
                cad.preenche_campos(lista.get(row));
                cad.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this,"ERRO AO CARREGAR ALTERAÇÃO DE REGISTRO"+e.getMessage());
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

        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelacolabores = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabeladocumentos = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABELA = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txpesquisa = new javax.swing.JTextField();
        participante = new javax.swing.JRadioButton();
        tipo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("pesquisa de atas");
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_add.png"))); // NOI18N
        jButton3.setText("INSERIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/printer.png"))); // NOI18N
        jButton4.setText("IMPRIMIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton8.setText("ALTERAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_delete.png"))); // NOI18N
        jButton5.setText("PRORROGAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton6.setText("SAIR");
        jPanel5.add(jButton6);

        getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        tabelacolabores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "REGISTRO", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacolabores.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelacolabores);

        jPanel4.add(jScrollPane2);

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        tabeladocumentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeladocumentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabeladocumentos);

        jPanel6.add(jScrollPane3);

        jPanel2.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel7.add(jLabel2);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/printer.png"))); // NOI18N
        jButton7.setText("IMPRIMIR TERMO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7);

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        TABELA.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        TABELA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DATA DO TREINAMENTO", "TIPO", "INICIO DO TREINAMENTO", "FIM DO TREINAMENTO", "VENCIMENTO", "PASTA DE ARQUIVAMENTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABELA.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TABELA.getTableHeader().setReorderingAllowed(false);
        TABELA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABELAMouseClicked(evt);
            }
        });
        TABELA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TABELAKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TABELA);

        jPanel3.add(jScrollPane1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("PESQUISAR:");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atualizar.png"))); // NOI18N
        jButton2.setText("ATUALIZAR");
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

        txpesquisa.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        participante.setText("PARTICIPANTE");
        participante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                participanteActionPerformed(evt);
            }
        });

        tipo.setText("TIPO DE TREINAMENTO");
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(participante)
                        .addGap(18, 18, 18)
                        .addComponent(tipo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(participante)
                            .addComponent(tipo)))
                    .addComponent(jButton2))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cadastrar_treinamento cad = new Cadastrar_treinamento(this, true);
        cad.insert = true;
        cad.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed

    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        atualizar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int row = TABELA.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            try {
                /*System.out.println("teste");
                List<Date> data = new TreinamentoDao().curdate();
                Date d = data.get(0);
                tre = lista.get(Integer.parseInt(TABELA.getValueAt(row, 0).toString()));
                List<BigInteger> diferenca = new TreinamentoDao().DATEDIFF(tre.getId());
                System.out.println("\n "+diferenca.get(0));
                BigInteger limite = BigInteger.valueOf(30);
                if(diferenca.get(0) < limite){*/
                Tela_prorrogacao tela = new Tela_prorrogacao(this, true);
                        tela.treinamento = lista.get(row);
                        tela.setVisible(true);
                /*}else{
                    Msg.alerta(this, "não pode ser realizada a prorrogação de treinamento antes de \n"
                            + "que falte 30 dias para seu vencimento");
                }*/
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "Erro ao abrir prorrogação"+e.getMessage());
            }
            /*try {
                List<Date> data = new TreinamentoDao().curdate();
                Date d = data.get(0);
                tre = lista.get(Integer.parseInt(TABELA.getValueAt(row,0).toString()));
                if (tre.getProx_treinamento().before(d)) {
                    new TreinamentoDao().alterar(alterar());
                    Loggin.arquivo_log("realizada a alteração de status referente a ata"+Integer.parseInt(TABELA.getValueAt(row,0).toString()));
                    Msg.informacao(this, "STATUS alterado com sucesso \n treinamente passa-se a ser invalido");
                } else {
                    Msg.alerta(this, "este treinamento não pode ser descartado");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao pegar data");
            }*/
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int row = TABELA.getSelectedRow();
            if (row < 0) {
                Msg.alerta(this, "selecione um registro");
            } else {
            //List<String> lista1 = new TreinamentoDao().titulo(Long.parseLong(TABELA.getValueAt(row, 0).toString()));
            //List<Get_setor> lista2 = new Daos_Nativos().setor(Integer.parseInt(TABELA.getValueAt(row, 0).toString()));
                            Rel_desc_car rel = new Rel_desc_car();
                            rel.setDocs( new Daos_Nativos().setor(Integer.parseInt(TABELA.getValueAt(row, 0).toString())));
                            rel.setIds(new DescricaoDao().documentos(Integer.parseInt(TABELA.getValueAt(row, 0).toString())));
            new Relatorio_ata(Long.valueOf(TABELA.getValueAt(row,0).toString()),Utils.Sistema_operacional(),Utils.concatenar(rel.getIds(), rel.getDocs()));
                Loggin.arquivo_log("realizou a impressão da ata"+Long.valueOf(TABELA.getValueAt(row,0).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao passar id \n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TABELAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABELAMouseClicked
        atualizard();
        atualizarc();
    }//GEN-LAST:event_TABELAMouseClicked

    private void TABELAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TABELAKeyReleased
        atualizard();
        atualizarc();
    }//GEN-LAST:event_TABELAKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            int row = TABELA.getSelectedRow();
            if (row < 0) {
                Msg.alerta(this, "selecione um registro");
            } else {
                new Relatorio_termo(Long.valueOf(TABELA.getValueAt(row,0).toString()),Utils.Sistema_operacional());
                Loggin.arquivo_log("realizado a impressão dos termos referentes a ata "+Long.valueOf(TABELA.getValueAt(row,0).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao passar id \n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        tipo.setSelected(true);
        participante.setSelected(false);
        this.pesquisa = "tipo";
    }//GEN-LAST:event_tipoActionPerformed

    private void participanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_participanteActionPerformed
        participante.setSelected(true);
        tipo.setSelected(false);
        this.pesquisa = "participante";
    }//GEN-LAST:event_participanteActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      alterar();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Carregar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carregar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carregar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carregar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Carregar_treinamento dialog = new Carregar_treinamento(new javax.swing.JFrame(), false);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton participante;
    private javax.swing.JTable tabelacolabores;
    private javax.swing.JTable tabeladocumentos;
    private javax.swing.JRadioButton tipo;
    private javax.swing.JTextField txpesquisa;
    // End of variables declaration//GEN-END:variables

}
