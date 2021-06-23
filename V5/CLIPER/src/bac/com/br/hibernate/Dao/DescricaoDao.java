/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.entidade.Descricao;
import bac.com.br.hibernate.utils.Get_setor;
import bac.com.br.hibernate.utils.Rel_desc_car;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 *
 * @author bruno
 */
public class DescricaoDao {
    public EntityManager em;
    
    public DescricaoDao(){
        em = Singleton.getconection();
    }
    public void inserir(Descricao des){
        em.getTransaction().begin();
        em.persist(des);
        em.getTransaction().commit();
    }
    public void alterar(Descricao des){
        em.getTransaction().begin();
        em.merge(des);
        em.getTransaction().commit();
    }
    public List<Setor> combo_setor(){
        em.getTransaction().begin();
        Query query = em.createQuery("select s from Setor s");
        List<Setor> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List<Funcao> combo_cargo(){
        em.getTransaction().begin();
        Query query = em.createQuery("select c from Funcao c");
        List<Funcao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List<Documento> traz_documentos(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select d from Documento d, Setor s where s.id = d.setor.id and s.nomeSetor like:likes and d.modificacao like 'ATIVO' order by d.titulo");
        query.setParameter("likes",texto);
        List<Documento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List<Descricao> traz_descricao(){
        em.getTransaction().begin();
        Query query = em.createQuery("select d from Descricao d order by d.idFuncaoId.funcao");
        List<Descricao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    
    public List<Descricao> pesquisa(String s){
        em.getTransaction().begin();
        Query query = em.createQuery("select d from Descricao d where d.idFuncaoId.funcao LIKE:likes order by d.idFuncaoId.funcao");
        query.setParameter("likes","%"+s+"%");
        List<Descricao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public Documento adiciona_documento(long i){
        em.getTransaction().begin();
        Documento c = em.find(Documento.class, i);
        em.getTransaction().commit();
        return c;
    }
    
    public int adiciona_documento_nome(String i){
        em.getTransaction().begin();
        Query query = em.createQuery("select e.id from Documento e where e.titulo like:likes");
        query.setParameter("likes", i);
        int id = query.getFirstResult();
        em.getTransaction().commit();
        return id;
    }
    public Descricao preenche(long i){
        em.getTransaction().begin();
        Descricao d = em.find(Descricao.class, i);
        em.getTransaction().commit();
        return d;
    }
    
    public List documentos_obrigatorios(Long i){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select documento.titulo from documento,descricao_documento,descricao,funcao where descricao.id_funcao_id = funcao.id and descricao.id = descricao_documento.descricao_id and documento.id = descricao_documento.id_documento_id and descricao.id =:likes order by documento.titulo");
        query.setParameter("likes", i);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List comparar_setores(Long i,String tipo){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select distinct SUBSTRING_INDEX(documento.titulo,'-',4) from documento,descricao_documento,colaborador,descricao,funcao,setor where descricao.id_funcao_id = funcao.id and descricao.id_setor_id = setor.id and colaborador.funcao_id = funcao.id and descricao.id = descricao_documento.descricao_id and documento.id=descricao_documento.id_documento_id and colaborador.id =:likes and documento.titulo like :tipo");
        query.setParameter("likes", i);
        query.setParameter("tipo", tipo);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List comparar_tipo(Long i){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select GROUP_CONCAT(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',2),'-',-2),'-',1) SEPARATOR ',') from documento,descricao_documento,colaborador,descricao,funcao,setor where descricao.id_funcao_id = funcao.id and descricao.id_setor_id = setor.id and colaborador.funcao_id = funcao.id and descricao.id = descricao_documento.descricao_id and documento.id=descricao_documento.id_documento_id and colaborador.id =:likes order by documento.titulo");
        query.setParameter("likes", i);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List comparar_numeros(Long i,String tipo){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select GROUP_CONCAT(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-1),'-',1)) from documento,descricao_documento,colaborador,descricao,funcao,setor where descricao.id_funcao_id = funcao.id and descricao.id_setor_id = setor.id and colaborador.funcao_id = funcao.id and descricao.id = descricao_documento.descricao_id and documento.id=descricao_documento.id_documento_id and colaborador.id =:likes and documento.titulo like :tipo order by documento.titulo");
        query.setParameter("likes", i);
        query.setParameter("tipo", tipo);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List impressao_documentos(Long i,String tipo){
        em.getTransaction().begin();
        List<Get_setor> lista = null;
        Query query = em.createNativeQuery("select SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-2),'-',1) as setor, SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-1) as numero from documento,descricao_documento,colaborador,descricao,funcao,setor where descricao.id_funcao_id = funcao.id and descricao.id_setor_id = setor.id and colaborador.funcao_id = funcao.id and descricao.id = descricao_documento.descricao_id and documento.id=descricao_documento.id_documento_id and colaborador.id =:likes and documento.titulo like:tipo order by documento.titulo");
        query.setParameter("likes", i);
        query.setParameter("tipo", tipo);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public void excluir_documentos(Long documento, Long descricao){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("delete descricao_documento from descricao_documento,documento,descricao where\n" +
"descricao_documento.descricao_id = descricao.id\n" +
"and descricao_documento.id_documento_id = documento.id\n" +
"and documento.id =:likes \n" +
"and descricao.id =:likess");
        query.setParameter("likes", documento);
        query.setParameter("likess", descricao);
        int excluir =query.executeUpdate();
        em.getTransaction().commit();
    }
    public List pegar_nome(String nome){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.id from documento where documento.titulo like:likes");
        query.setParameter("likes", nome);
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }   
    
    public Long salvar_historico(Long id){
        System.out.println("id_fun"+id);
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select descricao.id from descricao, funcao\n" +
"where descricao.id_funcao_id = funcao.id\n" +
"and funcao.id =:id");
        query.setParameter("id", id);
        Long id_des;
        if(query.getResultList().size() == 0){
        id_des = Long.valueOf(104);
        }else{
        List<BigInteger> lista = query.getResultList();
        id_des = Long.valueOf(lista.get(0).toString());
        }
        System.out.println("id_descricao"+id_des);
        em.getTransaction().commit();
        return id_des;
    }
    public Descricao seleciona(Long id){
        em.getTransaction().begin();
        Descricao des = em.find(Descricao.class, id);
        em.getTransaction().commit();
        return des;
    }
    
   
    public List numero(int id){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(documento.titulo,'-',4),'-',-1),'-',1) as titulo\n" +
"from treinamento, treinamento_documento, documento where\n" +
"documento.id = treinamento_documento.documento_id\n" +
"and treinamento.id = treinamento_documento.Treinamento_id\n" +
"and treinamento.id =:id order by titulo;");
        query.setParameter("id", id);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List documentos(int id){
        em.getTransaction().begin();
        List<String> lista = null;
        Query query = em.createNativeQuery("select SUBSTRING_INDEX(documento.titulo,'-',4) as titulo from documento, treinamento_documento,treinamento\n" +
"where documento.id = treinamento_documento.documento_id\n" +
"and treinamento_documento.Treinamento_id = treinamento.id\n" +
"and treinamento.id =:id");
        query.setParameter("id", id);
        lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
