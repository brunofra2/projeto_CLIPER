/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.utils;

import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Treinamento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno_l4kk8pa
 */
public class Daos_Nativos extends mysql{
    
    public void dropar_lista_doc(String titulo,int id)throws SQLException{
        this.open();
        //String sql="delete treinamento_documento.* from treinamento_documento where treinamento_documento.treinamento_id ="+id;
        String sql = "delete treinamento_documento.* from documento,treinamento_documento,treinamento where \n" +
"documento.titulo like '"+titulo+"'\n" +
"and treinamento_documento.documento_id = documento.id and treinamento_documento.Treinamento_id = treinamento.id\n" +
"and treinamento.id ="+id;
        System.err.println(sql);
        this.prepare(sql);
        this.execute();
        this.close();
    }
   
    public String lista_documentos(String texto)throws SQLException{
        this.open();
        String sql ="select documento.id as numero from documento where documento.titulo like '"+texto+"'";
        this.prepare(sql);
        this.executeQuery();
        this.getRS().next();
        String result = this.getRS().getString("numero");
        this.close();
        return result;
    }
    
    public String lista_colaboradores(String texto)throws SQLException{
        this.open();
        String sql ="select colaborador.id as numero from colaborador where colaborador.nome like '"+texto+"'";
        this.prepare(sql);
        this.executeQuery();
        this.getRS().next();
        String result = this.getRS().getString("numero");
        this.close();
        return result;
    }
    
    public void dropar_lista_col(String titulo,int id)throws SQLException{
        this.open();
        //String sql="delete treinamento_documento.* from treinamento_documento where treinamento_documento.treinamento_id ="+id;
        String sql = "delete treinamento_colaborador.* from colaborador,treinamento_colaborador,treinamento where\n" +
"colaborador.nome like'"+titulo+"'\n" +
"and treinamento_colaborador.colaborador_id = colaborador.id and treinamento_colaborador.Treinamento_id = treinamento.id\n" +
"and treinamento.id = "+id;
        System.err.println(sql);
        this.prepare(sql);
        this.execute();
        this.close();
    }
    
    public List<String> testar_conexao()throws SQLException{
        this.open();
        String sql = "select colaborador.nome from colaborador limit 10";
        this.prepare(sql);
        this.executeQuery();
        List<String> lista = new ArrayList<>();
        while (this.getRS().next()) {
            String nome = this.getRS().getString("nome");
            lista.add(nome);
        }
        this.close();
        return lista;
    }
    
    public List<get_set_Grafico> pegar_realizados(String ano)throws SQLException{
        this.open();
        String sql = "select DISTINCT d.titulo,c.nome from treinamento as t, treinamento_documento as td, documento as d, treinamento_colaborador as tc, colaborador as c where t.`status` = 'ATUALIZADO' and EXTRACT(YEAR FROM t.data_treinamento) ='"+ano+"' and t.id = td.Treinamento_id and td.documento_id = d.id and t.id = tc.Treinamento_id and tc.colaborador_id = c.id and d.modificacao like 'ATIVO' order by c.nome";
        this.prepare(sql);
        this.executeQuery();
        List<get_set_Grafico> lista = new ArrayList<>();
        
        while (this.getRS().next()) {            
            get_set_Grafico get = new get_set_Grafico();
            get.setTitulo_doc(this.getRS().getString("titulo"));
            get.setNome_col(this.getRS().getString("nome"));
            lista.add(get);
        }
        this.close();
        return lista;
    }
    
    public List<get_set_Grafico> pegar_obrigatorios()throws SQLException{
        this.open();
        String sql ="select DISTINCT documento.titulo,colaborador.nome from documento,descricao_documento,descricao,funcao,colaborador where documento.id = descricao_documento.id_documento_id and descricao_documento.descricao_id = descricao.id and descricao.id_funcao_id = funcao.id and funcao.id =colaborador.funcao_id and documento.modificacao like 'ATIVO' ORDER BY colaborador.nome";
        this.prepare(sql);
        this.executeQuery();
        List<get_set_Grafico> lista = new ArrayList<>();
        while(this.getRS().next()){
            get_set_Grafico get = new get_set_Grafico();
            get.setTitulo_doc(this.getRS().getString("titulo"));
            get.setNome_col(this.getRS().getString("nome"));
            lista.add(get);
        }
        this.close();
        return lista;
    }
    public List<Informacoes_horas> calcular_hora(String id,String ano)throws SQLException{
        this.open();
        String sql = "select treinamento.id, treinamento.horario_inicio as h1, treinamento.horario_final as h2, setor.nome_setor from treinamento, documento, treinamento_documento,setor\n" +
"where treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento_documento.documento_id = documento.id\n" +
"and documento.setor_id = setor.id\n" +
"and setor.nome_setor = '"+id+"'\n" +
"and YEAR(treinamento.data_treinamento) = '"+ano+"'\n" +
"GROUP BY treinamento.id";
        this.prepare(sql);
        this.executeQuery();
        List<Informacoes_horas> lista = new ArrayList<>();
        while(this.getRS().next()){
                Informacoes_horas inf = new Informacoes_horas();
            inf.setId(this.getRS().getString("id"));
            inf.setH1(this.getRS().getString("h1"));
            inf.setH2(this.getRS().getString("h2"));
            inf.setNome(this.getRS().getString("nome_setor"));
            lista.add(inf);
        }
        this.close();
        return lista;
    }
    
    // DAOS USADOS PARA O RELATÓRIO DE ANALISE CRITICA ABAIXO.
    public List<Analise> lista_doc_treinados(Long id,String ano)throws SQLException{
        this.open();
        String sql = "select GROUP_CONCAT(DISTINCT  documento.id) as texto, MONTH(treinamento.data_treinamento) as mes from documento,\n" +
"treinamento, treinamento_documento\n" +
"where documento.id = treinamento_documento.documento_id\n" +
"and treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento.`status` = \"ATUALIZADO\"\n" +
"and documento.modificacao = \"ATIVO\"\n" +
"and documento.titulo like 'IT-%'\n"+
"and MONTH(treinamento.data_treinamento) in ('02','03','04','05') \n" +
"and YEAR(treinamento.data_treinamento) = '"+ano+"'\n" +
"and documento.setor_id = "+id+" GROUP BY mes;";
        this.prepare(sql);
        this.executeQuery();
        List<Analise> lista = new ArrayList<Analise>();
        while (this.getRS().next()) {           
            Analise an = new Analise();
            an.setDocs(this.getRS().getString("texto"));
            an.setId(this.getRS().getInt("mes"));
            lista.add(an);
        }
        this.close();
        return lista;
    }
    public List<String> lista_doc_setor(Long id)throws SQLException{
        this.open();
        String sql = "select DISTINCT documento.id as texto from descricao, documento, descricao_documento,funcao\n" +
"where documento.id = descricao_documento.id_documento_id\n" +
"and descricao_documento.descricao_id = descricao.id\n" +
"and documento.modificacao = \"ATIVO\"\n" +
"and descricao.id_funcao_id = funcao.id\n" +
"and funcao.setor_id = "+id;
        this.prepare(sql);
        this.executeQuery();
        List<String> lista = new ArrayList<String>();
        while (this.getRS().next()) {           
             String s = new String();
            s = this.getRS().getString("texto");
            lista.add(s);
        }
        this.close();
        return lista;
    }
    
    public List impressao_documentos(int id,String tipo) throws SQLException{
         this.open();
         
       String sql ="select distinct SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-2),'-',1) as titulo,"
               + " SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-1) as numero "
               + "from documento,descricao_documento,colaborador,descricao,funcao,setor "
               + "where descricao.id_funcao_id = funcao.id and descricao.id_setor_id = setor.id "
               + "and colaborador.funcao_id = funcao.id "
               + "and descricao.id = descricao_documento.descricao_id "
               + "and documento.id=descricao_documento.id_documento_id "
               + "and colaborador.id ="+id+" and documento.titulo like '"+tipo+"'";
       this.prepare(sql);
        this.executeQuery();
        List<Get_setor> lista = new ArrayList<Get_setor>();
        while (this.getRS().next()) {           
             Get_setor setor = new Get_setor();
            setor.setTitulo(this.getRS().getString("titulo"));
            setor.setNumero(this.getRS().getString("numero"));
            lista.add(setor);
        }
        this.close();
       return lista;
    }
    
     public List setor(int id) throws SQLException{
         this.open();
         
       String sql ="select SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-2),'-',1) as titulo,  SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-1),'-',1) as numero\n" +
"from treinamento, treinamento_documento, documento where\n" +
"documento.id = treinamento_documento.documento_id\n" +
"and treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento.id ="+id+"";
       this.prepare(sql);
        this.executeQuery();
        List<Get_setor> lista = new ArrayList<Get_setor>();
        while (this.getRS().next()) {           
             Get_setor setor = new Get_setor();
            setor.setTitulo(this.getRS().getString("titulo"));
            setor.setNumero(this.getRS().getString("numero"));
            lista.add(setor);
        }
        this.close();
       return lista;
    }
    
}
