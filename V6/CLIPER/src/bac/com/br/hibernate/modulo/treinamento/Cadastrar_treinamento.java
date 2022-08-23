/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.modulo.treinamento;

import bac.com.br.hibernate.Dao.ColaboradorDao;
import bac.com.br.hibernate.Dao.DocumentoDao;
import bac.com.br.hibernate.Dao.ItensDao;
import bac.com.br.hibernate.Dao.PastaDao;
//import bac.com.br.hibernate.Dao.PgrDao;
import bac.com.br.hibernate.Dao.SetorDao;
import bac.com.br.hibernate.Dao.Tipo_treinamentoDao;
import bac.com.br.hibernate.Dao.TreinadorDao;
import bac.com.br.hibernate.Dao.TreinamentoDao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Itens;
import bac.com.br.hibernate.entidade.Pasta;
import bac.com.br.hibernate.entidade.Pgr;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.TipoTreinamento;
import bac.com.br.hibernate.entidade.Treinador;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.utils.Daos_Nativos;
import bac.com.br.hibernate.utils.Loggin;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.TextDocument;
import bac.com.br.hibernate.utils.Utils;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sun.util.calendar.Gregorian;

/**
 *
 * @author bruno
 */
public class Cadastrar_treinamento extends javax.swing.JDialog {

    private Carregar_treinamento pai;
    Treinamento trei;
    Colaborador col;
    Itens iten;
    Pasta pasta;
    List<Colaborador> lista_colaborador;
    List<Documento> lista_documento;
    List<Treinamento> lista_treinamento;
    List<Pasta> lista_pasta;
    List<Treinador> lista_treinador;
    List<Setor> lista_setor;
    List<String> adicionar_tabela_colaborador = new ArrayList<String>();
    List<String> adicionar_tabela_documento = new ArrayList<String>();
    List<String> inserir_colaborador = new ArrayList<String>();
    List<String> inserir_documento = new ArrayList<String>();
    List<Pgr> lista_pgr = new ArrayList<Pgr>();
    List<Itens> lista_itens = new ArrayList<Itens>();
    List<Date> data;
    private boolean alterar;
    protected String st;
    private boolean criar_pasta;
    private Treinador treinador;
    protected boolean insert;
    public boolean isCriar_pasta() {
        return criar_pasta;
    }

    public void setCriar_pasta(boolean criar_pasta) {
        this.criar_pasta = criar_pasta;
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    /**
     * Creates new form Cadastrar_treinamento
     */
    public Cadastrar_treinamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.iniciar();
        trei = new Treinamento();
        iten = new Itens();
    }

    public Cadastrar_treinamento(Carregar_treinamento parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
        this.iniciar();
        trei = new Treinamento();
        iten = new Itens();
    }

    private void atualizarcombo() {
        lista_treinador = new TreinadorDao().getlista("");
        cbtreinador.removeAllItems();
        cbtreinador.addItem("<...>");
        for (Treinador t : lista_treinador) {
            cbtreinador.addItem(t.getNome());
        }
    }
    List<TipoTreinamento> lista_tipo;
    private void atualzartipo(){
        try {
            txtipo.removeAllItems();
            txtipo.addItem("<...>");
            lista_tipo = new Tipo_treinamentoDao().combo();
            for (TipoTreinamento ti : lista_tipo) {
                txtipo.addItem(ti.getTipoTreinamento());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this,"erro ao atualizar combo tipo \n"+e.getMessage());
        }
    }
    private void atualizarcombo2() {
        lista_setor = new SetorDao().getlista("");
        cbdocumento.removeAllItems();
        cbdocumento.addItem("<...>");
        for (Setor t : lista_setor) {
            cbdocumento.addItem(t.getNomeSetor());
        }
    }

    private void atualizarcombo3() {
//        lista_pgr = new PgrDao().combo("");
        pgr.removeAllItems();
        pgr.addItem("<...>");
        for (Pgr t : lista_pgr) {
            pgr.addItem(t.getAno());
        }
    }

    private void atualizarcombo4(String s) {
        lista_itens = new ItensDao().itenscombo(s);
        item.removeAllItems();
        item.addItem("<...>");
        for (Itens t : lista_itens) {
            item.addItem(t.getDescricao());
        }
    }

    private void iniciar() {
        atualzartipo();
        atualizarcombo();
        atualizarcombo2();
        atualizarcombo3();
        trazcolaboradores("");
        trazdocumentos();
        Utils.maximizar(this);
        tabelacolaborador.getColumnModel().getColumn(0).setMinWidth(0);
        tabelacolaborador.getColumnModel().getColumn(0).setMaxWidth(0);
        tabeladocumento.getColumnModel().getColumn(0).setMinWidth(0);
        tabeladocumento.getColumnModel().getColumn(0).setMaxWidth(0);
        //txtipo.setDocument(new TextDocument(100));
        txinicio.setDocument(new TextDocument(50));
        txfim.setDocument(new TextDocument(50));
        txlocal.setDocument(new TextDocument(50));
    }

    private void pesquisadocumentos(String s) {
        try {
            lista_documento = new TreinamentoDao().getdocumento2(s);
            DefaultTableModel model = (DefaultTableModel) tabeladocumento.getModel();
            model.setNumRows(0);
            for (Documento d : lista_documento) {
                model.addRow(new Object[]{
                    d.getId(),
                    d.getTitulo(),
                    d.getTipo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro \n erro ao carregar tabela" + e.getMessage());
        }
    }

    private void trazdocumentos() {
        try {
            lista_documento = new TreinamentoDao().getdocumento();
            DefaultTableModel model = (DefaultTableModel) tabeladocumento.getModel();
            model.setNumRows(0);
            for (Documento d : lista_documento) {
                model.addRow(new Object[]{
                    d.getId(),
                    d.getTitulo(),
                    d.getTipo()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "Erro \n erro ao carregar tabela" + e.getMessage());
        }
    }

    private void trazcolaboradores(String s) {
        try {
            lista_colaborador = new TreinamentoDao().getcolaborador(s);
            DefaultTableModel model = (DefaultTableModel) tabelacolaborador.getModel();
            model.setNumRows(0);
            for (Colaborador c : lista_colaborador) {
                model.addRow(new Object[]{
                    c.getId(),
                    c.getNome()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao trazer os colaboradores \n" + e.getMessage());
        }
    }

    

    private void adicionar_colaborador() {
        //CODIGO DESATIVADO PARA REALIZAÇÃO DE MULTISELEÇÃO
        // REALIZA A SELECÃO DE APENAS UM REGISTRO POR VEZ
        /*int row = tabelacolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            String transfere = tabelacolaborador.getValueAt(row, 1).toString();
            String transfere2 = tabelacolaborador.getValueAt(row, 0).toString();
            inserir_colaborador.add(transfere2);
            adicionar_tabela_colaborador.add(transfere);
            DefaultTableModel model = (DefaultTableModel) listarcolaborador.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_colaborador) {
                model.addRow(new Object[]{
                    s
                });
            }
        }*/
        int rows[] = tabelacolaborador.getSelectedRows();
        if(rows.length == 0){
            Msg.alerta(this, "selecione um registro");
        }else{
             for(int i=0; i< rows.length; i++){
                 
                    String transfere = tabelacolaborador.getValueAt(rows[i], 1).toString();
                    String transfere2 = tabelacolaborador.getValueAt(rows[i], 0).toString();
                    inserir_colaborador.add(transfere2);
                    adicionar_tabela_colaborador.add(transfere);
                    DefaultTableModel model = (DefaultTableModel) listarcolaborador.getModel();
                    model.setNumRows(0);
                        int num = 0;
                        for (String s : adicionar_tabela_colaborador) {
                            model.addRow(new Object[]{
                                s
                            });
                            num++;
                        }
             }
        }
    }
private void remove_colaborador() {
        int row = listarcolaborador.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
                try {
                        String remove = listarcolaborador.getValueAt(row, 0).toString();

                        String id = new Daos_Nativos().lista_colaboradores(remove);
                        for(int i=0;i<inserir_colaborador.size();i++){
                            if(inserir_colaborador.get(i).equals(id)){
                                inserir_colaborador.remove(id);
                            }
                        }
                            adicionar_tabela_colaborador.remove(remove);
                            for(int i=0; i< lista_colaborador.size();i++){
                                Colaborador col = lista_colaborador.get(i);
                                if(col.getNome().equals(remove)){
                                    lista_colaborador.remove(col);
                                    System.err.println("removido");
                                }
                            }
                            if(insert != true){
                                excluir_col(remove,Integer.parseInt(trei.getId().toString()));
                            }
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao excluir item"+e.getMessage());
            }
            DefaultTableModel model = (DefaultTableModel) listarcolaborador.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_colaborador) {
                model.addRow(new Object[]{
                    s
                });
            }
        }
        /*int rows[] = listarcolaborador.getSelectedRows();
        if(rows.length == 0){
            Msg.alerta(this, "selecione um registro");
        }else{
            for(int i=0; i < rows.length; i++){
                String remove = listarcolaborador.getValueAt(rows[i], 1).toString();
                String numremove = listarcolaborador.getValueAt(rows[i], 0).toString();
                inserir_colaborador.remove(numremove);
                adicionar_tabela_colaborador.remove(remove);
                DefaultTableModel model = (DefaultTableModel) listarcolaborador.getModel();
                model.setNumRows(0);
                int num = 0;
                    for (String s : adicionar_tabela_colaborador) {
                        model.addRow(new Object[]{
                            inserir_colaborador.get(num),
                            s
                        });
                        num++;
                    }
                System.out.println("EXCLUIDO");
            }
        }*/
    }
    private void remove_documento() {
        int row = listardocumento.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            try {
                String remove = listardocumento.getValueAt(row, 0).toString();
                String id = new Daos_Nativos().lista_documentos(remove);
                for(int i=0;i<inserir_documento.size();i++){
                    if(inserir_documento.get(i).equals(id)){
                        inserir_documento.remove(id);
                    }
                }
                adicionar_tabela_documento.remove(remove);
                    for(int i=0; i< lista_documento.size();i++){
                        Documento doc = lista_documento.get(i);
                        if(doc.getTitulo().equals(remove)){
                            lista_documento.remove(doc);
                            System.err.println("removido");
                        }
                    }
                 if(insert != true){
                        excluir_doc(remove,Integer.parseInt(trei.getId().toString()));   
                 }         
            } catch (Exception e) {
                e.printStackTrace();
                Msg.erro(this, "erro ao remover item da lista"+e.getMessage());
            }
            DefaultTableModel model = (DefaultTableModel) listardocumento.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_documento) {
                model.addRow(new Object[]{
                    s
                });
            }
        }
        
        /*int rows[] = listardocumento.getSelectedRows();
        if(rows.length == 0){
            Msg.alerta(this, "selecione um registro");
        }else{
            for(int i=0;i < rows.length; i++){
                String remove = listardocumento.getValueAt(rows[i], 0).toString();
                inserir_documento.remove(remove);
                adicionar_tabela_documento.remove(remove);
                DefaultTableModel model = (DefaultTableModel) listardocumento.getModel();
                model.setNumRows(0);
                    for (String s : adicionar_tabela_documento) {
                        model.addRow(new Object[]{
                            s
                        });
                    }
            }
        }*/
    }

    private void adicionar_documento() {
        //CODIGO DESATIVADO PARA REALIZAÇÃO DE MULTISELEÇÃO
        // REALIZA A SELECÃO DE APENAS UM REGISTRO POR VEZ
        /*int row = tabeladocumento.getSelectedRow();
        if (row < 0) {
            Msg.alerta(this, "selecione um registro");
        } else {
            String transfere = tabeladocumento.getValueAt(row, 1).toString();
            String transfere2 = tabeladocumento.getValueAt(row, 0).toString();
            adicionar_tabela_documento.add(transfere);
            inserir_documento.add(transfere2);
            DefaultTableModel model = (DefaultTableModel) listardocumento.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_documento) {
                model.addRow(new Object[]{
                    s
                });
            }
        }*/
        int rows[] = tabeladocumento.getSelectedRows();
        if(rows.length == 0){
            Msg.alerta(this, "selecione um registro");
        }else{
            for(int i=0; i < rows.length; i++){
                String transfere = tabeladocumento.getValueAt(rows[i], 1).toString();
                String transfere2 = tabeladocumento.getValueAt(rows[i], 0).toString();
                adicionar_tabela_documento.add(transfere);
                inserir_documento.add(transfere2);
                DefaultTableModel model = (DefaultTableModel) listardocumento.getModel();
                model.setNumRows(0);
                    for (String s : adicionar_tabela_documento) {
                        model.addRow(new Object[]{
                            s
                        });
                    }
            }
        }
    }

    protected void excluir_col(String titulo,int id){
            
            try {
                        new Daos_Nativos().dropar_lista_col(titulo, id);
            } catch (SQLException ex) {
                Msg.erro(this,"erro"+ex.getMessage());
            }
    }
    
    protected void excluir_doc(String titulo,int id){
            
            try {
                        new Daos_Nativos().dropar_lista_doc(titulo,id);
            } catch (SQLException ex) {
                Msg.erro(this,"erro"+ex.getMessage());
            }
    }
    private Treinamento salvar() {
        trei.setTipo(txtipo.getSelectedItem().toString());
        trei.setHorarioInicio(txinicio.getText());
        trei.setHorarioFinal(txfim.getText());
        trei.setLocalidade(txlocal.getText());
        trei.setDataFinalizacao(DataFinalizacao.getDate());
        
        treinador = new TreinadorDao().seleciona(cbtreinador.getSelectedIndex());
        BigInteger b = new ItensDao().selecionarano(pgr.getSelectedItem().toString());
        System.out.println("____id ano"+b);
        trei.setTreinadorId(treinador);
        
        BigInteger a = new ItensDao().selecionariten(item.getSelectedItem().toString(),Integer.parseInt(String.valueOf(b)));
        System.out.println("____id item"+a);
        iten.setId(Long.parseLong(String.valueOf(a)));
        trei.setItensId(iten);
        //trei.setItens(lista_itens.get(item.getSelectedIndex() - 1));
        //trei.setTreinador(lista_treinador.get(item.getSelectedIndex() - 1));
        
        trei.setDataTreinamento(data_treinamento.getDate());
        trei.setTipoTreinamentoId(lista_tipo.get(txtipo.getSelectedIndex()-1));
        Date da = null;
        String s = "";
        s= new TreinamentoDao().addcurdate(data_treinamento.getDate(),lista_tipo.get(txtipo.getSelectedIndex()-1).getPeriodo());
        SimpleDateFormat ad = new SimpleDateFormat("yyyy-MM-dd");
        try {
            da = ad.parse(s);
        } catch (ParseException ex) {
            Msg.erro(this, "erro ao converter data \n"+ex.getMessage());
        }
        System.out.println("\ndata salvar"+da);
        trei.setProxTreinamento(da);
        trei.setStatus("ATUALIZADO");
        //ABAIXO ESTÃO AS CHAVES ESTRAGEIRAS
        //CHAVE ESTRANGEIRA COLABORADORES
        
        lista_colaborador.clear();
        for (int i = 0; i < inserir_colaborador.size(); i++) {
            long ex = Long.parseLong(inserir_colaborador.get(i));
            Colaborador c = new ColaboradorDao().adiciona_colaborador(ex);
            lista_colaborador.add(c);
        }
        trei.setColaboradorList(lista_colaborador);
        lista_documento.clear();
        //CHAVE ESTRANGEIRA DOCUMENTOS
        for (int i = 0; i < inserir_documento.size(); i++) {
            long ex = Long.parseLong(inserir_documento.get(i));
            Documento d = new DocumentoDao().adiciona_documentos(ex);
            System.out.println("documento: "+d.getTitulo());
            lista_documento.add(d);
        }

        trei.setDocumentoList(lista_documento);
        if(isAlterar() != true){
            criar_pasta();
        }
        return trei;
    }
    private boolean validacampos() {
        boolean va = true;
        String vazio = "campos vazios\n";
        if (txtipo.getSelectedIndex() <= 0) {
            va = false;
            vazio += "tipo de treinamento\n";
        }
        if (txinicio.getText().equals("")) {
            va = false;
            vazio += "horario inicial";
        }
        if (txfim.getText().equals("")) {
            va = false;
            vazio += "horario de conclusao\n";
        }
        if (txlocal.getText().equals("")) {
            va = false;
            vazio += "local de realização\n";
        }
        if(DataFinalizacao.getDate().before(data_treinamento.getDate()) || DataFinalizacao.getDate().equals(null)){
            va= false;
            vazio+= "data de termino não pode ser anterior ha de inicio";
        }
        if (cbtreinador.getSelectedIndex() == 0) {
            va = false;
            vazio += "Responsavel\n";
        }
        if (inserir_colaborador.size() <= 0) {
            va = false;
            vazio += "colaboradores participantes\n";
        }
        if (inserir_documento.size() <= 0) {
            va = false;
            vazio += "documentos estudados\n";
        }
        if(pgr.getSelectedIndex() <= 0){
            va = false;
            vazio += "ano do cronograma\n";
        }
        if(item.getSelectedIndex() <= 0){
                
            va = false;
            vazio += "item do cronograma\n";
            
        }
        if(txtipo.getSelectedIndex() <= 0){
            va = false;
            vazio += "tipo de treinamento";
        }
        if (va == false) {
            Msg.alerta(this, vazio);
        }
        return va;
    }

    private void criar_pasta() {
        try {
            lista_pasta = new PastaDao().max();
            String w = String.valueOf(lista_pasta.get(0));
            System.out.println("\n" + lista_pasta.get(0));
            lista_pasta = new PastaDao().cont(String.valueOf(lista_pasta.get(0)));
            System.out.println("\n" + lista_pasta.get(0));
            String a = String.valueOf(lista_pasta.get(0));
            int b = Integer.parseInt(a);
            if (b <= 49 && b > 0) {
                long c = Long.parseLong(w);
                pasta = new PastaDao().seleciona(c);
                trei.setPastaId(pasta);
            } else {
                new Criar_pasta(this, true).setVisible(true);
                lista_pasta = new PastaDao().max();
                String d = String.valueOf(lista_pasta.get(0));
                long f = Long.parseLong(d);
                System.out.println("passou por aqui \n" + f);
                pasta = new PastaDao().seleciona(f);
                trei.setPastaId(pasta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao selecionar pasta" + e.getMessage());
        }
    }

    private void concluir() {
        if (validacampos() == true) {
            if (isAlterar() == true) {
                new TreinamentoDao().alterar(salvar());
                Msg.informacao(this, "alterado com sucesso");
            } else {
                try {
                    new TreinamentoDao().inserir(salvar());
                    Loggin.arquivo_log("criado uma nova ata com a numeração"+trei.getId());
                    Msg.informacao(this, "cadastrado com sucesso");
                } catch (Exception e) {
                    e.printStackTrace();
                    Msg.erro(this, "erro ao cadastrar treinamento \n" + Utils.getErro(e));
                }
            }
            pai.atualizar();
            dispose();
        }
    }

    
    
    // FUNÇÕES PARA ALTERAÇÃO DE ATAS
    protected void preenche_campos(Treinamento tre){
        this.trei = tre;
        txtipo.setSelectedItem(trei.getTipo());
        txinicio.setText(trei.getHorarioInicio());
        txfim.setText(trei.getHorarioFinal());
        data_treinamento.setDate(trei.getDataTreinamento());
        DataFinalizacao.setDate(trei.getDataFinalizacao());
        txlocal.setText(trei.getLocalidade());
        cbtreinador.setSelectedItem(trei.getTreinadorId().getNome());
        String ano = new TreinamentoDao().pegar_ano(trei.getItensId().getId(), trei.getId());
        pgr.setSelectedItem(ano);
        atualizarcombo4(ano);
        item.setSelectedItem(trei.getItensId().getDescricao());
        atualizarc(trei.getId());
        atualizard(trei.getId());
    }
    
    protected void atualizard(Long id) {
        try {
            List<String> lista1 = new TreinamentoDao().titulo(id);
            List<String> lista2 = new TreinamentoDao().id_documento(id);
            
            for (int i = 0; i < lista1.size(); i++) {
                String p = String.valueOf(lista1.get(i));
                String a = String.valueOf(lista2.get(i));
                        
                        adicionar_tabela_documento.add(p);
                        inserir_documento.add(a);
                        DefaultTableModel model = (DefaultTableModel) listardocumento.getModel();
                        model.setNumRows(0);
                        for (String s : adicionar_tabela_documento) {
                            model.addRow(new Object[]{
                                s
                            });
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(this, "erro ao tentar atualizar tabela \n" + Utils.getErro(e));
        }
    }
    protected void atualizarc(Long id) {
        try {
            List<String> lista1 = new TreinamentoDao().id(id);
            List<String> lista2 = new TreinamentoDao().nome(id);

            for (int i = 0; i < lista2.size(); i++) {
                String b = String.valueOf(lista2.get(i));
                String c = String .valueOf(lista1.get(i));
            inserir_colaborador.add(c);
            adicionar_tabela_colaborador.add(b);
            DefaultTableModel model = (DefaultTableModel) listarcolaborador.getModel();
            model.setNumRows(0);
            for (String s : adicionar_tabela_colaborador) {
                model.addRow(new Object[]{
                    s
                });
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtipo = new componentes.UJComboBox();
        jLabel8 = new javax.swing.JLabel();
        data_treinamento = new org.jdesktop.swingx.JXDatePicker();
        jLabel11 = new javax.swing.JLabel();
        DataFinalizacao = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        txinicio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txfim = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txlocal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbtreinador = new componentes.UJComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pgr = new componentes.UJComboBox();
        jLabel10 = new javax.swing.JLabel();
        item = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelacolaborador = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listarcolaborador = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabeladocumento = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listardocumento = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txcolaborador = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        cbdocumento = new componentes.UJComboBox();
        jPanel17 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/treinamento/icone.png"));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(5, 4, 5, 5));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TIPO DE TREINAMENTO:");
        jPanel3.add(jLabel3);

        txtipo.setEditable(true);
        txtipo.setAutocompletar(true);
        jPanel3.add(txtipo);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DATA TREINAMENTO:");
        jPanel3.add(jLabel8);

        data_treinamento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel3.add(data_treinamento);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DATA DE FINALIZAÇÃO");
        jPanel3.add(jLabel11);

        DataFinalizacao.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        DataFinalizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataFinalizacaoActionPerformed(evt);
            }
        });
        jPanel3.add(DataFinalizacao);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("HORARIO DE INICIO:");
        jPanel3.add(jLabel4);

        txinicio.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel3.add(txinicio);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("HORARIO DE FINALIZAÇÃO:");
        jPanel3.add(jLabel5);

        txfim.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel3.add(txfim);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LOCAL DE REALIZAÇÃO:");
        jPanel3.add(jLabel7);

        txlocal.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel3.add(txlocal);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("RESPONSAVEL:");
        jPanel3.add(jLabel6);

        cbtreinador.setEditable(true);
        cbtreinador.setAutocompletar(true);
        cbtreinador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel3.add(cbtreinador);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("ANO DO PGR:");
        jPanel3.add(jLabel9);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        pgr.setEditable(true);
        pgr.setAutocompletar(true);
        pgr.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jPanel4.add(pgr);

        jPanel3.add(jPanel4);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ITEM DO PGR:");
        jPanel3.add(jLabel10);

        item.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        item.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<...>" }));
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMouseClicked(evt);
            }
        });
        jPanel3.add(item);

        jButton11.setText("CRIAR DOCUMENTO");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("CRIAR COLABORADOR");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12)
                    .addComponent(jButton11))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        tabelacolaborador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabelacolaborador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelacolaborador.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabelacolaborador.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelacolaborador);

        jPanel7.add(jScrollPane1);

        jPanel6.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        listarcolaborador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        listarcolaborador.setModel(new javax.swing.table.DefaultTableModel(
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
        listarcolaborador.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listarcolaborador.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(listarcolaborador);

        jPanel8.add(jScrollPane2);

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        tabeladocumento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        tabeladocumento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabeladocumento.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(tabeladocumento);

        jPanel9.add(jScrollPane3);

        jPanel6.add(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        listardocumento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        listardocumento.setModel(new javax.swing.table.DefaultTableModel(
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
        listardocumento.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(listardocumento);

        jPanel10.add(jScrollPane4);

        jPanel6.add(jPanel10);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/add.png"))); // NOI18N
        jButton2.setText("ADICIONAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton2);

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_delete.png"))); // NOI18N
        jButton8.setText("REMOVER");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton8);

        jPanel11.add(jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/add.png"))); // NOI18N
        jButton1.setText("ADICIONAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton1);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/vcard_delete.png"))); // NOI18N
        jButton7.setText("REMOVER");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton7);

        jPanel11.add(jPanel13);

        jPanel5.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOME:");

        txcolaborador.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton3.setText("BUSCAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atualizar.png"))); // NOI18N
        jButton9.setText("ATUALIZAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txcolaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txcolaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jButton9))
                .addGap(2, 2, 2))
        );

        jPanel14.add(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel2.setText("AREA:");

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/folder_explore.png"))); // NOI18N
        jButton4.setText("BUSCAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/atualizar.png"))); // NOI18N
        jButton10.setText("ATUALIZAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cbdocumento.setEditable(true);
        cbdocumento.setAutocompletar(true);
        cbdocumento.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        cbdocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton4)
                    .addComponent(jButton10)
                    .addComponent(cbdocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel14.add(jPanel16);

        jPanel5.add(jPanel14, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/disk.png"))); // NOI18N
        jButton5.setText("CONCLUIR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton5);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bac/com/br/hibernate/imagens/cancel.png"))); // NOI18N
        jButton6.setText("CANCELAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton6);

        jPanel1.add(jPanel17, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        adicionar_colaborador();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        adicionar_documento();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        concluir();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txcolaborador.getText().equals("")) {
            Msg.alerta(this, "preencha o campo com o nome do colaborador");
        } else {
            trazcolaboradores(txcolaborador.getText());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (cbdocumento.getSelectedIndex() == 0) {
            Msg.alerta(this, "selecione uma area a ser buscada");
        } else {
            pesquisadocumentos(cbdocumento.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        remove_colaborador();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        remove_documento();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        trazdocumentos();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        trazcolaboradores("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMouseClicked
       int row = pgr.getSelectedIndex();
        if(row <= 0){
            Msg.alerta(this, "selecione o ano do pgr a ser contemplado");
        }else{
            atualizarcombo4(pgr.getSelectedItem().toString());
        }
    }//GEN-LAST:event_itemMouseClicked

    private void DataFinalizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataFinalizacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DataFinalizacaoActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        new Cadastrar_documento(this, true).setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        new Cadastrar_colaboradores(this, true).setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void cbdocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbdocumentoActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastrar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastrar_treinamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cadastrar_treinamento dialog = new Cadastrar_treinamento(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXDatePicker DataFinalizacao;
    private componentes.UJComboBox cbdocumento;
    private componentes.UJComboBox cbtreinador;
    private org.jdesktop.swingx.JXDatePicker data_treinamento;
    private javax.swing.JComboBox<String> item;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable listarcolaborador;
    private javax.swing.JTable listardocumento;
    private componentes.UJComboBox pgr;
    private javax.swing.JTable tabelacolaborador;
    private javax.swing.JTable tabeladocumento;
    private javax.swing.JTextField txcolaborador;
    private javax.swing.JTextField txfim;
    private javax.swing.JTextField txinicio;
    private javax.swing.JTextField txlocal;
    private componentes.UJComboBox txtipo;
    // End of variables declaration//GEN-END:variables

    void preenche_campos(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
