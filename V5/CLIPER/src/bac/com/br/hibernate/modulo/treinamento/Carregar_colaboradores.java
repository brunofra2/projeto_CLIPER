/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DescricaoDao;
import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Curso;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.relatorios.Colaboradores_treinados;
import bac.com.br.hibernate.relatorios.Relatorio_descricao;
//import bac.com.br.hibernate.relatorios.pendencias;
import bac.com.br.hibernate.utils.Daos_Nativos;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Rel_desc_car;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuário
 */
public class Carregar_colaboradores extends javax.swing.JDialog {

    private Tela_principal pai;
    List<Colaborador> lista;
    List<Historico> lista_historico;
    List<Documento> lista_documento;
    Colaborador col;
    private String pesquisa;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form Carregar_colaboradores
     */
    public Carregar_colaboradores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Utils.maximizar(this);
        atualizatabela();
        col = new Colaborador();
        atualizacombo();
    }

    public Carregar_colaboradores(Tela_principal parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        Utils.maximizar(this);
        atualizatabela();
        col = new Colaborador();
        atualizacombo();
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
                    if(this.pesquisa.equals("registro")){
                    try {
                        DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
                        model.setNumRows(0);
                        lista = new ColaboradorDao().getregistro(txpesquisa.getText());
                        System.err.println("Teste de registro");
                        for (Colaborador c : lista) {
                            model.addRow(new Object[]{
                                c.getNome(),
                                c.getRegistro(),
                                c.getDataNasc()!= null?format.format(c.getDataNasc()):c.getDataNasc(),
                                c.getDataAdm()!= null?format.format(c.getDataAdm()):c.getDataAdm(),
                                c.getStatus(),
                                c.getFuncaoId().getFuncao()
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Msg.erro(this, "erro ao atualizar a tabela\nErro:" + e.getMessage());
                    }
                    
                }else{
                    try {
                        DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
                        model.setNumRows(0);
                        lista = new ColaboradorDao().getsetor(txpesquisa.getText());
                        System.err.println("Teste de setores");
                        for (Colaborador c : lista) {
                            model.addRow(new Object[]{
                                c.getNome(),
                                c.getRegistro(),
                                c.getDataNasc()!= null?format.format(c.getDataNasc()):c.getDataNasc(),
                                c.getDataAdm()!= null?format.format(c.getDataAdm()):c.getDataAdm(),
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
                    his.getPeriodoInicio() != null?form.format(his.getPeriodoInicio()): his.getPeriodoInicio(),
                    his.getPeriodoFim() != null?form.format(his.getPeriodoFim()): his.getPeriodoFim()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + e.getMessage());
        }
    }
        private void desativar(){
            int row = tabelacolaborador.getSelectedRow();
            if(row < 0){
                Msg.alerta(this, "selecione um registro");
            }else{
              col = lista.get(row);
              col.setStatus("INATIVO");
                if(Msg.confirmar(this, "deseja realmente desativar esse colaborador") == true){
                    try {
                    new ColaboradorDao().alterar(col);
                Loggin.arquivo_log("desativado cadastro de um colaborador com o id"+lista.get(row));
                    Msg.informacao(this, "colaborador desativado com sucesso");
                    atualizatabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao desativar colaborador");
                }
                }else{
                    return;
                }
            }
        }
        private void ativar(){
            int row = tabelacolaborador.getSelectedRow();
            if(row < 0){
                Msg.alerta(this, "selecione um registro");
            }else{
              col = lista.get(row);
              col.setStatus("ATIVO");
                if(Msg.confirmar(this, "deseja realmente reativar esse colaborador") == true){
                    try {
                    new ColaboradorDao().alterar(col);
                Loggin.arquivo_log("ativado cadastro de um colaborador com o id"+lista.get(row));
                    Msg.informacao(this, "colaborador reativado com sucesso");
                    atualizatabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao reativar colaborador");
                }
                }else{
                    return;
                }
            }
        }
            protected void atualizard() {
        try {
            int row = tabelacolaborador.getSelectedRow();
            List<String> lista1 = new ColaboradorDao().titulo_documento(lista.get(row).getId());
            List<String> lista2 = new ColaboradorDao().tipo_documento(lista.get(row).getId());

            DefaultTableModel model = (DefaultTableModel) tabeladocumento.getModel();
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
    List<String> obrigatorio = new ArrayList<String>();
    List<String> falta = new ArrayList<String>();
    List<String> realizado = new ArrayList<String>();
    String d;
    String listar;
    private String comparacao(Long a,String s){
     d= "";
    listar = "";
        try {
            obrigatorio = new DocumentoDao().obrigatorios(a);
            realizado = new DocumentoDao().realizados_ano(a,s);
            
            for(String re : realizado){
                d += re;
                System.out.println(re);
            }
            System.out.println("_______________________________");
            for (String da : obrigatorio) {
                if(d.contains(da)){
                    System.out.println("contem"+da);
                }else{
                    falta.add(da);
                }
            }
            for(String b : falta){
                System.out.println("falta:"+b);
                listar +=b+",";
            }
                System.out.println("\n_______________________________\n");
                System.out.println("lista:"+listar);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro de comparação \n"+e.getMessage());
        }
        return listar;
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
        jButton12 = new javax.swing.JButton();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        cbano = new componentes.UJComboBox();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacolaborador = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        ativo = new javax.swing.JCheckBox();
        inativo = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeladocumento = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelacurso = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

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
        jButton11.setText("DESCRIÇÃO DE CARGO");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11);

        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/printer.png"))); // NOI18N
        jButton12.setText("HISTORICO");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12);

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

        jPanel10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel10.setLayout(new java.awt.GridLayout(4, 0, 5, 5));

        cbano.setEditable(true);
        cbano.setAutocompletar(true);
        jPanel10.add(cbano);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atividade.jpg"))); // NOI18N
        jButton5.setText("TREINAMENTOS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton5);

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/warning.png"))); // NOI18N
        jButton10.setText("PENDENCIAS");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton10);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/add.png"))); // NOI18N
        jButton7.setText("ADD HISTORICO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton7);

        jPanel4.add(jPanel10);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

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

        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);

        tabeladocumento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabeladocumento.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeladocumento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabeladocumento.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabeladocumento);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setText("DOCUMENTOS");
        jPanel6.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel6, java.awt.BorderLayout.EAST);

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

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro na tabela");
        } else {
            Cadastrar_colaboradores c = new Cadastrar_colaboradores(this, true);
            c.setAlterar(true);
            c.col = lista.get(row);
            c.preenchecampos(lista.get(row));
            System.out.println(lista.get(row).getId());
            c.listar_salvos(lista.get(row).getId());
            c.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cadastrar_colaboradores c = new Cadastrar_colaboradores(this, true);
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            /*
            
            adicionar_cursos c = new adicionar_cursos(this, true);
            System.out.println("\n" + lista.get(row));
            c.selecionar(lista.get(row));
            c.setVisible(true);
            */
            
            Adicionar_periodos add = new Adicionar_periodos(this, true);
    //        add.listar(lista.get(row).getId());
            add.seleciona(lista.get(row));
            add.setVisible(true);

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tabelacolaboradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelacolaboradorMouseClicked
        atualizar();
        atualizard();
    }//GEN-LAST:event_tabelacolaboradorMouseClicked

    private void tabelacolaboradorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelacolaboradorKeyReleased
        atualizar();
        atualizard();
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
        atualizard();
    }//GEN-LAST:event_tabelacolaboradorMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            int row = tabelacolaborador.getSelectedRow();
            if (row < 0) {
                Msg.alerta(this, "selecione um registro");
            } else {
                if(cbano.getSelectedIndex() <= 0){
                    Msg.alerta(this, "selecione o periodo desejado");
                }else{
                    
                falta.clear();
                    //comparacao(lista.get(row).getId(),cbano.getSelectedItem().toString());
                new Colaboradores_treinados(Long.valueOf(lista.get(row).getId()),comparacao(lista.get(row).getId(),cbano.getSelectedItem().toString()),Utils.Sistema_operacional());
                Loggin.arquivo_log("realizou a impressão da lista de documentos treinados por"+lista.get(row).getNome());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao passar id \n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       try {
            int row = tabelacolaborador.getSelectedRow();
            if (row < 0) {
                Msg.alerta(this, "selecione um registro");
            } else {
                if(cbano.getSelectedIndex() <= 0){
                    Msg.alerta(this, "selecione o periodo desejado");
                }else{
                    falta.clear();
//                new pendencias(Long.valueOf(lista.get(row).getId()),comparacao(lista.get(row).getId(),cbano.getSelectedItem().toString()),Utils.Sistema_operacional());
                System.err.println("\nlista"+falta.size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao passar id \n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txpesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpesquisaKeyReleased
        pesquisa();
    }//GEN-LAST:event_txpesquisaKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int row = tabelacolaborador.getSelectedRow();
                if (row < 0) {
                    Msg.alerta(this, "selecione um registro");
                } else {
                    if(Msg.confirmar(this, "deseja imprir o histórico de descricões ?") == true){
                      try {
                          Multipla_impressao mult =   new Multipla_impressao(this, true);
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
       
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
            new Multipla_impressao(this, true).setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void setorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setorActionPerformed
        rnome.setSelected(false);
        registro.setSelected(false);
        setor.setSelected(true);
        this.pesquisa = "setor";
    }//GEN-LAST:event_setorActionPerformed

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
            java.util.logging.Logger.getLogger(Carregar_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carregar_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carregar_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carregar_colaboradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Carregar_colaboradores dialog = new Carregar_colaboradores(new javax.swing.JFrame(), true);
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
    private componentes.UJComboBox cbano;
    private javax.swing.JCheckBox inativo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JRadioButton registro;
    private javax.swing.JRadioButton rnome;
    private javax.swing.JRadioButton setor;
    private javax.swing.JTable tabelacolaborador;
    private javax.swing.JTable tabelacurso;
    private javax.swing.JTable tabeladocumento;
    private javax.swing.JTextField txpesquisa;
    // End of variables declaration//GEN-END:variables
}
