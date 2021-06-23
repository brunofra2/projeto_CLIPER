/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.relatorios.Relatorio_detalhado;
import bac.com.br.hibernate.utils.Daos_Nativos;
import bac.com.br.hibernate.utils.Informacoes_horas;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tela_principal extends javax.swing.JFrame {

    Treinamento tre;
    List<Setor> lista = new ArrayList<>();
    /**
     * Creates new form Tela_principal
     */
    public Tela_principal() {
        initComponents();
        tre = new Treinamento();
       // iniciar();
    }
    
    private void iniciar(){
        
       lista = new SetorDao().getlista("");
       
        for (Setor setor : lista) {
                Locale lol = new Locale("pt","BR");
                NumberFormat format = NumberFormat.getNumberInstance(lol);
                format.setMinimumFractionDigits(2);
        
          try {
            List<Informacoes_horas> lista = new Daos_Nativos().calcular_hora(setor.getNomeSetor(),"2017");
            
                String h1 = "";
                String h2 = "";
                Double soma = 0.0;
            for (Informacoes_horas treinamento : lista) {
                if(treinamento.getH1().contains("h")){
                    h1 = treinamento.getH1().replace("h", ".");
                }else{
                    if(treinamento.getH2().contains(":")){
                        h1 = treinamento.getH2().replace(":", ".");
                    }
                }
                    
                if(treinamento.getH2().contains("h")){
                     h2 = treinamento.getH2().replace("h", ".");
                }else{
                    if(treinamento.getH2().contains(":")){
                     h2 = treinamento.getH2().replace(":", ".");
                    }
                }
                Double hora_inicio;
                Double hora_fim;
                Double calcular;
                Double sobra_minutos;
                Double recalculo;
                hora_inicio = Double.parseDouble(h1);
                hora_fim = Double.parseDouble(h2);
                sobra_minutos = hora_fim - Math.floor(hora_fim);
                calcular = Math.floor(hora_fim)-0.40 - hora_inicio + sobra_minutos;
                
                if((calcular+0.40) >= (Math.floor(calcular)+1)){
                sobra_minutos = calcular - Math.floor(calcular);
                recalculo = Math.floor(calcular) + 1 + (sobra_minutos -0.60) ;
                }else{
                   recalculo = calcular; 
                }
                    String a = format.format(recalculo);
                if(a.equals("0,60")){
                    recalculo = 1.00;
                }
               System.out.println(format.format(hora_fim)+"-"+format.format(hora_inicio)+"="+format.format(recalculo));
                soma = soma + recalculo;
            }
                Double minutos;
                if((soma+0.40)>= (Math.floor(soma)+1)){
                    minutos = soma - Math.floor(soma);
                    soma = Math.floor(soma)+1+(minutos-0.60);
                }else{
                    soma = soma;
                }
             System.out.println(format.format(soma));
             
             if(setor.getNomeSetor().equals("ADMINISTRACAO")){
                 VALOR1.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("ALMOXARIFADO")){
                 VALOR2.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("BENEFICIAMENTO")){
                 VALOR3.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("BRITAGEM")){
                 VALOR4.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("CONTABILIDADE")){
                 VALOR5.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("LABORATORIO MINA")){
                 VALOR6.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("LABORATORIO USINA")){
                 VALOR7.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("MANUTENCAO MINA")){
                 VALOR8.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("MANUTENCAO USINA")){
                 VALOR9.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("MEIO AMBIENTE")){
                 VALOR10.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("MOINHO E SECADOR")){
                 VALOR11.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("OPERACAO MINA")){
                 VALOR13 .setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("OPERACAO USINA")){
                 VALOR14.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("PESQUISA")){
                 VALOR15.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("RECURSOS HUMANOS")){
                 VALOR16.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("SEGURANCA MINA")){
                 VALOR17.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("SEGURANCA PATRIMONIAL")){
                 VALOR18.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("SEGURANCA USINA")){
                 VALOR19.setText(format.format(soma));
             }
             if(setor.getNomeSetor().equals("TRANSPORTE")){
                 VALOR20.setText(format.format(soma));
             }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao calcular horas \n"+e.getMessage());
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        colaborador = new javax.swing.JButton();
        setores = new javax.swing.JButton();
        cargo = new javax.swing.JButton();
        descricao_cargo = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        empresa = new javax.swing.JButton();
        treinador = new javax.swing.JButton();
        pasta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        treinamento = new javax.swing.JButton();
        pgr = new javax.swing.JButton();
        usuario = new javax.swing.JButton();
        ajuda = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        administracao = new javax.swing.JLabel();
        VALOR1 = new javax.swing.JLabel();
        moi_sec = new javax.swing.JLabel();
        VALOR11 = new javax.swing.JLabel();
        almoxarifado = new javax.swing.JLabel();
        VALOR2 = new javax.swing.JLabel();
        operacao_mina = new javax.swing.JLabel();
        VALOR13 = new javax.swing.JLabel();
        beneficiamento = new javax.swing.JLabel();
        VALOR3 = new javax.swing.JLabel();
        operacao_usina = new javax.swing.JLabel();
        VALOR14 = new javax.swing.JLabel();
        britagem = new javax.swing.JLabel();
        VALOR4 = new javax.swing.JLabel();
        pesquisa = new javax.swing.JLabel();
        VALOR15 = new javax.swing.JLabel();
        contabilidade = new javax.swing.JLabel();
        VALOR5 = new javax.swing.JLabel();
        recursos_humanos = new javax.swing.JLabel();
        VALOR16 = new javax.swing.JLabel();
        laboratorio_mina = new javax.swing.JLabel();
        VALOR6 = new javax.swing.JLabel();
        seguranca_mina = new javax.swing.JLabel();
        VALOR17 = new javax.swing.JLabel();
        laboratorio_usina = new javax.swing.JLabel();
        VALOR7 = new javax.swing.JLabel();
        seguranca_patr = new javax.swing.JLabel();
        VALOR18 = new javax.swing.JLabel();
        manutencao_mina = new javax.swing.JLabel();
        VALOR8 = new javax.swing.JLabel();
        seguranca_usina = new javax.swing.JLabel();
        VALOR19 = new javax.swing.JLabel();
        manutencao_usina = new javax.swing.JLabel();
        VALOR9 = new javax.swing.JLabel();
        transporte = new javax.swing.JLabel();
        VALOR20 = new javax.swing.JLabel();
        meio_ambiente = new javax.swing.JLabel();
        VALOR10 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/mineiro.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CLIPER ");
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(13, 1, 5, 5));

        colaborador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        colaborador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/fucionario.png"))); // NOI18N
        colaborador.setText("COLABORADORES");
        colaborador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        colaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaboradorActionPerformed(evt);
            }
        });
        jPanel2.add(colaborador);

        setores.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        setores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/setor.png"))); // NOI18N
        setores.setText("SETORES");
        setores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setoresActionPerformed(evt);
            }
        });
        jPanel2.add(setores);

        cargo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        cargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cargo.png"))); // NOI18N
        cargo.setText("CARGOS");
        cargo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoActionPerformed(evt);
            }
        });
        jPanel2.add(cargo);

        descricao_cargo.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        descricao_cargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/papel.png"))); // NOI18N
        descricao_cargo.setText("DESCRIÇOES DE CARGOS");
        descricao_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricao_cargoActionPerformed(evt);
            }
        });
        jPanel2.add(descricao_cargo);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/papel.png"))); // NOI18N
        jButton3.setText("DOCUMENTOS");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        empresa.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/empresa.png"))); // NOI18N
        empresa.setText("EMPRESAS");
        empresa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaActionPerformed(evt);
            }
        });
        jPanel2.add(empresa);

        treinador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        treinador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/treinador.png"))); // NOI18N
        treinador.setText("TREINADORES");
        treinador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        treinador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treinadorActionPerformed(evt);
            }
        });
        jPanel2.add(treinador);

        pasta.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        pasta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/pasta.png"))); // NOI18N
        pasta.setText("PASTAS");
        pasta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastaActionPerformed(evt);
            }
        });
        jPanel2.add(pasta);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/relat.png"))); // NOI18N
        jButton1.setText("TIPO DE TREINAMENTO");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        treinamento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        treinamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/treinamento.png"))); // NOI18N
        treinamento.setText("TREINAMENTOS");
        treinamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        treinamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treinamentoActionPerformed(evt);
            }
        });
        jPanel2.add(treinamento);

        pgr.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        pgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/calendario.png"))); // NOI18N
        pgr.setText("CRONOGRAMAS");
        pgr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pgrActionPerformed(evt);
            }
        });
        jPanel2.add(pgr);

        usuario.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/usuario.png"))); // NOI18N
        usuario.setText("USUARIOS");
        usuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        jPanel2.add(usuario);

        ajuda.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        ajuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/ajuda.png"))); // NOI18N
        ajuda.setText("AJUDA");
        ajuda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ajuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajudaActionPerformed(evt);
            }
        });
        jPanel2.add(ajuda);

        jPanel1.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel5.setLayout(new java.awt.GridLayout(12, 4, 5, 5));

        administracao.setText("ADMINISTRACAO");
        jPanel5.add(administracao);
        jPanel5.add(VALOR1);

        moi_sec.setText("MOINHO E SECADOR");
        jPanel5.add(moi_sec);
        jPanel5.add(VALOR11);

        almoxarifado.setText("ALMOXARIFADO");
        jPanel5.add(almoxarifado);
        jPanel5.add(VALOR2);

        operacao_mina.setText("OPERACAO MINA");
        jPanel5.add(operacao_mina);
        jPanel5.add(VALOR13);

        beneficiamento.setText("BENEFICIAMENTO");
        jPanel5.add(beneficiamento);
        jPanel5.add(VALOR3);

        operacao_usina.setText("OPERACAO USINA");
        jPanel5.add(operacao_usina);
        jPanel5.add(VALOR14);

        britagem.setText("BRITAGEM");
        jPanel5.add(britagem);
        jPanel5.add(VALOR4);

        pesquisa.setText("PESQUISA");
        jPanel5.add(pesquisa);
        jPanel5.add(VALOR15);

        contabilidade.setText("CONTABILIDADE");
        jPanel5.add(contabilidade);
        jPanel5.add(VALOR5);

        recursos_humanos.setText("RECURSOS HUMANOS");
        jPanel5.add(recursos_humanos);
        jPanel5.add(VALOR16);

        laboratorio_mina.setText("LABORATORIO MINA");
        jPanel5.add(laboratorio_mina);
        jPanel5.add(VALOR6);

        seguranca_mina.setText("SEGURANCA MINA");
        jPanel5.add(seguranca_mina);
        jPanel5.add(VALOR17);

        laboratorio_usina.setText("LABORATORIO USINA");
        jPanel5.add(laboratorio_usina);
        jPanel5.add(VALOR7);

        seguranca_patr.setText("SEGURANCA PATRIMONIAL");
        jPanel5.add(seguranca_patr);
        jPanel5.add(VALOR18);

        manutencao_mina.setText("MANUTENCAO MINA");
        jPanel5.add(manutencao_mina);
        jPanel5.add(VALOR8);

        seguranca_usina.setText("SEGURANCA USINA");
        jPanel5.add(seguranca_usina);
        jPanel5.add(VALOR19);

        manutencao_usina.setText("MANUTENCAO USINA");
        jPanel5.add(manutencao_usina);
        jPanel5.add(VALOR9);

        transporte.setText("TRANSPORTE");
        jPanel5.add(transporte);
        jPanel5.add(VALOR20);

        meio_ambiente.setText("MEIO AMBIENTE");
        jPanel5.add(meio_ambiente);
        jPanel5.add(VALOR10);
        jPanel5.add(jLabel40);
        jPanel5.add(jLabel41);

        jPanel6.add(jPanel5, java.awt.BorderLayout.CENTER);

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("HORAS DE TREINAMENTO REALIZADO EM ");
        jPanel6.add(jLabel42, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(jPanel6);

        jPanel1.add(jPanel4, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("RELATORIOS");

        jMenuItem1.setText("TREINAMENTOS ANUAIS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setText("ESTATISTICA DE DOCUMENTOS");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator3);

        jMenuItem4.setText("ESTATISTICA DE COLABORADORES");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator4);

        jMenuItem2.setText("ESTATISTICA ATAS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setText("ESTATISTICA TREINADOS ");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("jMenuItem6");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaboradorActionPerformed
        new Carregar_colaboradores(this, true).setVisible(true);
    }//GEN-LAST:event_colaboradorActionPerformed

    private void cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoActionPerformed
        new Carregar_funcoes(this, true).setVisible(true);
    }//GEN-LAST:event_cargoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Carregar_Documentos(this, true).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaActionPerformed
        new Carregar_empresa(this, true).setVisible(true);
    }//GEN-LAST:event_empresaActionPerformed

    private void treinadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treinadorActionPerformed
        new Carregar_treinador(this, true).setVisible(true);
    }//GEN-LAST:event_treinadorActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        new Carregar_usuario(this, true).setVisible(true);
    }//GEN-LAST:event_usuarioActionPerformed

    private void treinamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_treinamentoActionPerformed
        new Carregar_treinamento(this, true).setVisible(true);
    }//GEN-LAST:event_treinamentoActionPerformed

    private void pastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastaActionPerformed
        new carregar_pastas(this, true).setVisible(true);
    }//GEN-LAST:event_pastaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new Grafico_treinamentos(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void setoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setoresActionPerformed
        new Carregar_setores(this, true).setVisible(true);
    }//GEN-LAST:event_setoresActionPerformed

    private void pgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pgrActionPerformed
        new pesquisar_pgr(this, true).setVisible(true);
    }//GEN-LAST:event_pgrActionPerformed

    private void ajudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajudaActionPerformed
        try {
            String caminho = System.getProperty("user.dir")+"\\Help.chm";
            //System.getProperty("user.dir")+"\\Help.chm";
            Runtime.getRuntime().exec("cmd /c start " + caminho);
            System.out.println(caminho);
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro ao gerar o arquivo de ajuda" + e.getMessage());
        }
    }//GEN-LAST:event_ajudaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new Grafico_colaborador(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new Grafico_documentos(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void descricao_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricao_cargoActionPerformed
        new Carregar_descricoes(this, true).setVisible(true);
    }//GEN-LAST:event_descricao_cargoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Carregar_periodo(this, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Grafico_por_setores(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new Grafico_treinados(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new Relatorio_detalhado(Utils.Sistema_operacional(),"RECURSOS HUMANOS","2018");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VALOR1;
    private javax.swing.JLabel VALOR10;
    private javax.swing.JLabel VALOR11;
    private javax.swing.JLabel VALOR13;
    private javax.swing.JLabel VALOR14;
    private javax.swing.JLabel VALOR15;
    private javax.swing.JLabel VALOR16;
    private javax.swing.JLabel VALOR17;
    private javax.swing.JLabel VALOR18;
    private javax.swing.JLabel VALOR19;
    private javax.swing.JLabel VALOR2;
    private javax.swing.JLabel VALOR20;
    private javax.swing.JLabel VALOR3;
    private javax.swing.JLabel VALOR4;
    private javax.swing.JLabel VALOR5;
    private javax.swing.JLabel VALOR6;
    private javax.swing.JLabel VALOR7;
    private javax.swing.JLabel VALOR8;
    private javax.swing.JLabel VALOR9;
    private javax.swing.JLabel administracao;
    private javax.swing.JButton ajuda;
    private javax.swing.JLabel almoxarifado;
    private javax.swing.JLabel beneficiamento;
    private javax.swing.JLabel britagem;
    protected javax.swing.JButton cargo;
    protected javax.swing.JButton colaborador;
    private javax.swing.JLabel contabilidade;
    private javax.swing.JButton descricao_cargo;
    protected javax.swing.JButton empresa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel laboratorio_mina;
    private javax.swing.JLabel laboratorio_usina;
    private javax.swing.JLabel manutencao_mina;
    private javax.swing.JLabel manutencao_usina;
    private javax.swing.JLabel meio_ambiente;
    private javax.swing.JLabel moi_sec;
    private javax.swing.JLabel operacao_mina;
    private javax.swing.JLabel operacao_usina;
    private javax.swing.JButton pasta;
    private javax.swing.JLabel pesquisa;
    protected javax.swing.JButton pgr;
    private javax.swing.JLabel recursos_humanos;
    private javax.swing.JLabel seguranca_mina;
    private javax.swing.JLabel seguranca_patr;
    private javax.swing.JLabel seguranca_usina;
    protected javax.swing.JButton setores;
    private javax.swing.JLabel transporte;
    protected javax.swing.JButton treinador;
    private javax.swing.JButton treinamento;
    protected javax.swing.JButton usuario;
    // End of variables declaration//GEN-END:variables
}
