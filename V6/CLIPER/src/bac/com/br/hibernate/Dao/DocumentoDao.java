/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class DocumentoDao {
    private EntityManager em;
    public DocumentoDao(){
        em = Singleton.getconection();
    }
    public void inserir(Documento doc){
        em.getTransaction().begin();
        em.persist(doc);
        em.getTransaction().commit();
    }
    public void alterar(Documento doc){
        em.getTransaction().begin();
        em.merge(doc); 
        em.getTransaction().commit();
    }
    public void excluir(Documento doc){
        em.getTransaction().begin();
        em.remove(doc);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e from Documento e where e.titulo LIKE:likes and e.modificacao like 'ATIVO' ORDER BY e.titulo");
        query.setParameter("likes", "%"+texto+"%");
        List<Documento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Documento adiciona_documentos(long i){
        em.getTransaction().begin();
        Documento d = em.find(Documento.class, i);
        em.getTransaction().commit();
        return d;
    }
    public List todos(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.id from documento\n" +
"where documento.modificacao LIKE \"ATIVO\"");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List grafico(String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.id\n" +
"from documento, treinamento_documento, treinamento\n" +
"where treinamento_documento.documento_id = documento.id\n" +
"and documento.modificacao like \"ATIVO\"\n" +
"and treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento.`status` like \"ATUALIZADO\" \n"
                + "and year(treinamento.data_treinamento) = "+ano+"\n" +
"GROUP BY documento.id;");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public BigInteger estatus(String s){
        em.getTransaction().begin();
        BigInteger b = null;
        Query query = em.createNativeQuery("select documento.id from documento,treinamento,treinamento_documento where documento.id = treinamento_documento.documento_id and treinamento_documento.Treinamento_id = treinamento.id and treinamento.`status` like 'ATUALIZADO' and documento.id =:likes");
        query.setParameter("likes", s);
        List<BigInteger> lista = query.getResultList();
        if(lista.size() > 0){
            b = lista.get(0);
        }
        em.getTransaction().commit();
        return b;
    }
    
    public List colaboradores_nulos(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinamento_documento.documento_id from documento LEFT OUTER join treinamento_documento on documento.id = treinamento_documento.documento_id GROUP by documento.titulo");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List colaboradores_nulos2(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.titulo from documento LEFT OUTER join treinamento_documento on documento.id = treinamento_documento.documento_id GROUP by documento.titulo");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List colaboradores(Long a,String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select colaborador.nome from colaborador,treinamento_colaborador,treinamento,treinamento_documento, documento where colaborador.id = treinamento_colaborador.colaborador_id and treinamento_colaborador.Treinamento_id = treinamento.id and treinamento.id = treinamento_documento.Treinamento_id and documento.id = treinamento_documento.documento_id and documento.id =:likes and EXTRACT(YEAR FROM treinamento.data_treinamento) = :ano group by colaborador.nome");
        query.setParameter("likes",a);
        query.setParameter("ano", ano);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List obrigatorios(Long texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DISTINCT documento.titulo from documento,descricao_documento,descricao,funcao,colaborador where documento.id = descricao_documento.id_documento_id and descricao_documento.descricao_id = descricao.id and descricao.id_funcao_id = funcao.id and funcao.id =colaborador.funcao_id and documento.modificacao like 'ATIVO' and colaborador.id =:likes");
        query.setParameter("likes",texto);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List realizados(Long texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DISTINCT d.titulo from treinamento as t, treinamento_documento as td, documento as d, treinamento_colaborador as tc, colaborador as c where t.`status` = 'ATUALIZADO' and t.id = td.Treinamento_id and td.documento_id = d.id and t.id = tc.Treinamento_id and tc.colaborador_id = c.id and c.id =:likes order by d.titulo");
        query.setParameter("likes", texto);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List realizados_ano(Long texto,String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DISTINCT d.titulo from treinamento as t, treinamento_documento as td, documento as d, treinamento_colaborador as tc, colaborador as c where t.`status` = 'ATUALIZADO' and EXTRACT(YEAR FROM t.data_treinamento) =:ano and t.id = td.Treinamento_id and td.documento_id = d.id and t.id = tc.Treinamento_id and tc.colaborador_id = c.id and d.modificacao like 'ATIVO' and c.id =:likes order by d.titulo");
        query.setParameter("likes", texto);
        query.setParameter("ano", ano);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List grafico_setor_valor(String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select COUNT( DISTINCT treinamento.id) from treinamento,treinamento_documento,documento,setor\n" +
"where treinamento_documento.Treinamento_id = treinamento.id and treinamento_documento.documento_id = documento.id\n" +
"and YEAR(treinamento.data_treinamento) =:ano \n" +
"and documento.setor_id = setor.id GROUP BY setor.id");
        query.setParameter("ano", ano);
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List grafico_setor_nome(String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select setor.nome_setor from treinamento,treinamento_documento,documento,setor\n" +
"where treinamento_documento.Treinamento_id = treinamento.id and treinamento_documento.documento_id = documento.id\n" +
"and YEAR(treinamento.data_treinamento) =:ano \n" +
"and documento.setor_id = setor.id GROUP BY setor.id");
        query.setParameter("ano", ano);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    // DAO PARA REALIZAR A COMPARAÇÃO DAS PENDENCIAS
    public List concat_obrigatorios(Long id){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select GROUP_CONCAT(documento.id) from descricao, documento, descricao_documento,funcao\n" +
"where documento.id = descricao_documento.id_documento_id\n" +
"and descricao_documento.descricao_id = descricao.id\n" +
"and descricao.id_funcao_id = funcao.id \n" +
"and funcao.setor_id =:id \n" +
"group BY funcao.funcao;");
        query.setParameter("id", id);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List concat_realizados(Long id,String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select GROUP_CONCAT(documento.id) from documento,\n" +
"treinamento, treinamento_documento\n" +
"where documento.id = treinamento_documento.documento_id\n" +
"and treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento.`status` = \"ATUALIZADO\"\n"+ 
"and year(treinamento.data_treinamento) = "+ano+"\n" +
"and documento.setor_id =:id GROUP BY treinamento.id;");
        query.setParameter("id", id);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List listar_documentos(String resultado){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.titulo from documento where documento.id in ("+resultado+")");
   
        //query.setParameter("lista",resultado);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List todos(Long id){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.id from descricao, documento, descricao_documento,funcao\n" +
"where documento.id = descricao_documento.id_documento_id\n" +
"and descricao_documento.descricao_id = descricao.id\n" +
"and documento.modificacao =\"ATIVO\"\n" +
"and descricao.id_funcao_id = funcao.id \n" +
"and funcao.setor_id =:long\n" +
"group BY documento.id;");
        query.setParameter("long",id);
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    
    
    
    // CRIAR RELATORIO ESTATISTICO
    public Integer quantidade_documento(Long id,String month, String year){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select count(DISTINCT documento.id) from documento,setor, treinamento_documento, treinamento where documento.titulo like \"IT-%\"\n" +
"and documento.modificacao like 'ATIVO'\n" +
"and documento.setor_id = setor.id \n" +
"and documento.id = treinamento_documento.documento_id\n" +
"and treinamento_documento.Treinamento_id = treinamento.id\n" +
"and MONTH(treinamento.data_treinamento) =:month\n" +
"and year(treinamento.data_treinamento) =:year\n" +
"and treinamento.status like 'ATUALIZADO'"+
"and setor.id =:id");
        query.setParameter("id", id);
        query.setParameter("month", month);
        query.setParameter("year", year);
        List<BigInteger> lista = query.getResultList();
        Integer i =Integer.parseInt( lista.get(0).toString());
        em.getTransaction().commit();
        return i;
    }
}
