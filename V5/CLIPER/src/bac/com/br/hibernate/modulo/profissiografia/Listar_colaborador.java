/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.profissiografia;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.HistoricoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.modulo.profissiografia.excel.Impressao_ppp;
import bac.com.br.hibernate.modulo.treinamento.Multipla_impressao;
import bac.com.br.hibernate.relatorios.Relatorio_descricao;
import bac.com.br.hibernate.utils.Daos_Nativos;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Rel_desc_car;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuário
 */
public class Listar_colaborador extends javax.swing.JDialog {

    private Tela_principalP pai;
    List<Colaborador> lista;
    List<Historico> lista_historico;
    List<Documento> lista_documento;
    Colaborador col;
    private String pesquisa;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form Carregar_colaboradores
     */
    public Listar_colaborador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utils.maximizar(this);
        atualizatabela();
        col = new Colaborador();
    }

    public Listar_colaborador(Tela_principalP parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
        atualizatabela();
        col = new Colaborador();

    }

    String min;
    String max;

    protected void atualizatabela() {
        try {
            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
            model.setNumRows(0);
            lista = new ColaboradorDao().getlista("");
            System.out.println(lista.size());
            for (Colaborador c : lista) {
                model.addRow(new Object[]{
                    c.getNome(),
                    c.getRegistro(),
                    format.format(c.getDataNasc()),
                    format.format(c.getDataAdm()),
                    c.getStatus(),
                    c.getFuncaoId().getFuncao()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
        }
    }

    private void atualisaativo() {
        try {

            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
            model.setNumRows(0);
            lista = new ColaboradorDao().ativos_inativos("ATIVO");
            for (Colaborador c : lista) {
                model.addRow(new Object[]{
                    c.getNome(),
                    c.getRegistro(),
                    format.format(c.getDataNasc()),
                    format.format(c.getDataAdm()),
                    c.getStatus(),
                    c.getFuncaoId().getFuncao()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
        }

    }

    private void atualisainativo() {
        try {
            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
            model.setNumRows(0);
            lista = new ColaboradorDao().ativos_inativos("INATIVO");
            for (Colaborador c : lista) {
                model.addRow(new Object[]{
                    c.getNome(),
                    c.getRegistro(),
                    format.format(c.getDataNasc()),
                    format.format(c.getDataAdm()),
                    c.getStatus(),
                    c.getFuncaoId().getFuncao()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
        }
    }

    private void ativo_inativo() {
        if (ativo.isSelected() == true) {
            atualisaativo();
            if (inativo.isSelected() == true) {
                atualizatabela();
            }
        } else {
            if (inativo.isSelected() == true) {
                atualisainativo();
                if (ativo.isSelected() == true) {
                    atualizatabela();
                }
            }
        }
        if (ativo.isSelected() == false && inativo.isSelected() == false) {
            atualizatabela();
        }
    }
    protected void excluir(){
        int row = tabelacurso.getSelectedRow();
        if(row < 0){
            Msg.alerta(this, "selecione um registro da tabela");
        }else{
            if(Msg.confirmar(this, "deseja realmente deletar esta informação?") == false){
                return;
            }else{
                try {
                    new HistoricoDao().excluir(lista_historico.get(row));
                    Msg.informacao(this, "item excluido com sucesso");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao excluir item"+e.getMessage());
                }
            }
        }
    }

    private void pesquisa() {
        if ((pesquisa != "nome") && (pesquisa != "registro") && (pesquisa != "setor")) {
            Msg.alerta(this, "selecione o tipo de busca a ser realizada");
        } else {
            if (txpesquisa.getText().equals("")) {
                Msg.alerta(this, "preencha o campo para realizar a busca");
            } else {
                if (this.pesquisa.equals("nome")) {
                    try {
                        DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
                        model.setNumRows(0);
                        lista = new ColaboradorDao().getlista(txpesquisa.getText());
                        System.err.println("Teste de nome");
                        for (Colaborador c : lista) {
                            model.addRow(new Object[]{
                                c.getNome(),
                                c.getRegistro(),
                                format.format(c.getDataNasc()),
                                format.format(c.getDataAdm()),
                                c.getStatus(),
                                c.getFuncaoId().getFuncao()
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
                    }
                } else {
                    if (this.pesquisa.equals("registro")) {
                        try {
                            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
                            model.setNumRows(0);
                            lista = new ColaboradorDao().getregistro(txpesquisa.getText());
                            System.err.println("Teste de registro");
                            for (Colaborador c : lista) {
                                model.addRow(new Object[]{
                                    c.getNome(),
                                    c.getRegistro(),
                                    c.getDataNasc() != null ? format.format(c.getDataNasc()) : c.getDataNasc(),
                                    c.getDataAdm() != null ? format.format(c.getDataAdm()) : c.getDataAdm(),
                                    c.getStatus(),
                                    c.getFuncaoId().getFuncao()
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
                        }

                    } else {
                        try {
                            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
                            model.setNumRows(0);
                            lista = new ColaboradorDao().getsetor(txpesquisa.getText());
                            System.err.println("Teste de setores");
                            for (Colaborador c : lista) {
                                model.addRow(new Object[]{
                                    c.getNome(),
                                    c.getRegistro(),
                                    c.getDataNasc() != null ? format.format(c.getDataNasc()) : c.getDataNasc(),
                                    c.getDataAdm() != null ? format.format(c.getDataAdm()) : c.getDataAdm(),
                                    c.getStatus(),
                                    c.getFuncaoId().getFuncao()
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
    }

    protected void atualizar() {
        try {
            int row = tabelacolaborador.getSelectedRow();
            lista_historico = new ColaboradorDao().cursos(lista.get(row).getId());
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
            DefaultTableModel model = (DefaultTableModel) tabelacurso.getModel();
            model.setNumRows(0);
            for (Historico his : lista_historico) {
                model.addRow(new Object[]{
                    his.getIdCargo().getFuncao(),
                    his.getPeriodoInicio() != null ? form.format(his.getPeriodoInicio()) : his.getPeriodoInicio(),
                    his.getPeriodoFim() != null ? form.format(his.getPeriodoFim()) : his.getPeriodoFim()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + e.getMessage());
        }
    }

    private void desativar() {
        int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            col = lista.get(row);
            col.setStatus("INATIVO");
            if (Msg.confirmar(this, "deseja realmente desativar esse colaborador") == true) {
                try {
                    new ColaboradorDao().alterar(col);
                    Loggin.arquivo_log("desativado cadastro de um colaborador com o id" + lista.get(row));
                    Msg.informacao(this, "colaborador desativado com sucesso");
                    atualizatabela();
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
        int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            col = lista.get(row);
            col.setStatus("ATIVO");
            if (Msg.confirmar(this, "deseja realmente reativar esse colaborador") == true) {
                try {
                    new ColaboradorDao().alterar(col);
                    Loggin.arquivo_log("ativado cadastro de um colaborador com o id" + lista.get(row));
                    Msg.informacao(this, "colaborador reativado com sucesso");
                    atualizatabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao reativar colaborador");
                }
            } else {
                return;
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

        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txpesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        rnome = new javax.swing.JRadioButton();
        registro = new javax.swing.JRadioButton();
        setor = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacolaborador = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        ativo = new javax.swing.JCheckBox();
        inativo = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelacurso = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_add.png"))); // NOI18N
        jButton3.setText("INSERIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_edit.png"))); // NOI18N
        jButton4.setText("ALTERAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/printer.png"))); // NOI18N
        jButton11.setText("IMPRIMIR PPP");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setText("DESCRIÇÃO DE CARGOS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/status_online.png"))); // NOI18N
        jButton9.setText("ATIVAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9);

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/status_offline.png"))); // NOI18N
        jButton8.setText("DESATIVAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton6.setText("SAIR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setText("PESQUISAR:");

        txpesquisa.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        txpesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txpesquisaKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atualizar.png"))); // NOI18N
        jButton2.setText("ATUALIZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        rnome.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        rnome.setText("NOME");
        rnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rnomeActionPerformed(evt);
            }
        });

        registro.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        registro.setText("REGISTRO");
        registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroActionPerformed(evt);
            }
        });

        setor.setText("SETOR");
        setor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txpesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(rnome)
                        .addGap(18, 18, 18)
                        .addComponent(registro)
                        .addGap(18, 18, 18)
                        .addComponent(setor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registro)
                    .addComponent(rnome)
                    .addComponent(setor))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        tabelacolaborador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabelacolaborador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nome", "registro", "nascimento", "admissao", "status", "funcao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacolaborador.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabelacolaborador.getTableHeader().setReorderingAllowed(false);
        tabelacolaborador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelacolaboradorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelacolaboradorMousePressed(evt);
            }
        });
        tabelacolaborador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelacolaboradorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelacolaborador);

        jPanel8.add(jScrollPane1);

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        ativo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        ativo.setText("ATIVOS");
        ativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ativoActionPerformed(evt);
            }
        });
        jPanel9.add(ativo);

        inativo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        inativo.setText("INATIVOS");
        inativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inativoActionPerformed(evt);
            }
        });
        jPanel9.add(inativo);

        jPanel5.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(null);

        tabelacurso.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabelacurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CARGO", "INICIO DA ATIVIDADE", "FIM DA ATIVIDADE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacurso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tabelacurso);

        jPanel7.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setText("HISTORICO DE CARGOS");
        jPanel7.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jButton7.setText("REMOVER ATIVIDADES");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro na tabela");
        } else {
            Inserir_colaboradores c = new Inserir_colaboradores(this, true);
            c.setAlterar(true);
            c.col = lista.get(row);
            c.preenchecampos(lista.get(row));
            System.out.println(lista.get(row).getId());
            c.listar_salvos(lista.get(row).getId());
            c.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Inserir_colaboradores c = new Inserir_colaboradores(this, true);
        c.setVisible(true);
        c.setAlterar(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        atualizatabela();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tabelacolaboradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelacolaboradorMouseClicked
        atualizar();
    }//GEN-LAST:event_tabelacolaboradorMouseClicked

    private void tabelacolaboradorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelacolaboradorKeyReleased
        atualizar();

    }//GEN-LAST:event_tabelacolaboradorKeyReleased

    private void registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroActionPerformed
        rnome.setSelected(false);
        registro.setSelected(true);
        setor.setSelected(false);
        this.pesquisa = "registro";
    }//GEN-LAST:event_registroActionPerformed

    private void rnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rnomeActionPerformed
        rnome.setSelected(true);
        registro.setSelected(false);
        setor.setSelected(false);
        this.pesquisa = "nome";
    }//GEN-LAST:event_rnomeActionPerformed

    private void ativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ativoActionPerformed
        ativo_inativo();
    }//GEN-LAST:event_ativoActionPerformed

    private void inativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inativoActionPerformed
        ativo_inativo();
    }//GEN-LAST:event_inativoActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        desativar();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ativar();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tabelacolaboradorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelacolaboradorMousePressed
        atualizar();
    }//GEN-LAST:event_tabelacolaboradorMousePressed

    private void txpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpesquisaKeyReleased
        pesquisa();
    }//GEN-LAST:event_txpesquisaKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
            int row = tabelacolaborador.getSelectedRow();
            if(row < 0){
                Msg.alerta(this, "selecione um registro");
            }else{
                Impressao_ppp imp = new Impressao_ppp();
                
                imp.criar_excel(lista.get(row));
                
                
                //File filce = new File("D:\\"+lista.get(row).getRegistro()+".xls");
                File filce = new File("S:\\RH\\PPP_CLIPER\\"+lista.get(row).getRegistro()+".xls");
                try {
                    Desktop.getDesktop().open(filce);
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao abrir arquivo gerado"+e.getMessage());
                }
            }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void setorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setorActionPerformed
        rnome.setSelected(false);
        registro.setSelected(false);
        setor.setSelected(true);
        this.pesquisa = "setor";
    }//GEN-LAST:event_setorActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       int row = tabelacolaborador.getSelectedRow();
                if (row < 0) {
                    Msg.alerta(this, "selecione um registro");
                } else {
                    if(Msg.confirmar(this, "deseja imprir o histórico de descricões ?") == true){
                      try {
                          Multipla_impressao mult = new Multipla_impressao(this,true);
                          mult.atualizar(lista.get(row).getId());
                          mult.selecionar(lista.get(row).getId());
                          mult.setVisible(true);
                      } catch (Exception e) {
                          e.printStackTrace();
                          Msg.erro(this, "erro ao carregar tela de impressão"+e.getMessage());
                      }
                  }else{
                      try {
                            Rel_desc_car rel = new Rel_desc_car();
                            rel.setDocs(new Daos_Nativos().impressao_documentos(Integer.parseInt(lista.get(row).getId().toString()),"IT-%"));
                            rel.setIds(new DescricaoDao().comparar_setores(lista.get(row).getId(),"IT-%"));
                            //rel.setNums(new DescricaoDao().comparar_numeros(lista.get(row).getId(),"IT%"));
                            String operacional = Utils.concatenar(rel.getIds(),rel.getDocs());
                            System.out.println(operacional);
                            
                            Rel_desc_car rel2 = new Rel_desc_car();
                            rel2.setDocs(new Daos_Nativos().impressao_documentos(Integer.parseInt(lista.get(row).getId().toString()),"IS-%"));
                            rel2.setIds(new DescricaoDao().comparar_setores(lista.get(row).getId(),"IS-%"));
                            //rel2.setNums(new DescricaoDao().comparar_numeros(lista.get(row).getId(),"IS%"));
                            //String seguranca = Utils.concatenar(rel2.getIds(),rel2.getDocs());
                           // System.out.println(seguranca);
                            System.out.println("____ documentos _____");
                            if(rel.getDocs().size() > 0){
                                new Relatorio_descricao(lista.get(row).getId(),operacional,"",Utils.Sistema_operacional());
                            }else{
                                Msg.alerta(this, "descrição selecionada não contém documentação obrigatória");
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Msg.erro(this, "Desculpe Descricao de cargo não encontrada \n" + e.getMessage());
                    }
                  }
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        excluir();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Listar_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listar_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listar_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listar_colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Listar_colaborador dialog = new Listar_colaborador(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox ativo;
    private javax.swing.JCheckBox inativo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton registro;
    private javax.swing.JRadioButton rnome;
    private javax.swing.JRadioButton setor;
    private javax.swing.JTable tabelacolaborador;
    private javax.swing.JTable tabelacurso;
    private javax.swing.JTextField txpesquisa;
    // End of variables declaration//GEN-END:variables
}
