/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Setor;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class SetorDao {
    EntityManager em;
     public SetorDao(){
         em = Singleton.getconection();
     }
     public void inserir(Setor se){
         em.getTransaction().begin();
         em.persist(se);
         em.getTransaction().commit();
     }
     public void alterar(Setor se){
         em.getTransaction().begin();
         em.merge(se);
         em.getTransaction().commit();
     }
     public void excluir(Setor se){
         em.getTransaction().begin();
         em.remove(se);
         em.getTransaction().commit();
     }
     public List getlista(String s){
         em.getTransaction().begin();
         Query query = em.createQuery("SELECT e FROM Setor e where e.situacao like 'ATIVO' and e.nomeSetor LIKE:s order by e.nomeSetor asc");
         query.setParameter("s","%"+s+"%");
         List<Setor> lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     
     public List titulo(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.titulo from documento,setor where setor.id = documento.setor_id and setor.id=:likes order by documento.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List tipo(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.tipo from documento,setor where setor.id = documento.setor_id and setor.id=:likes order by documento.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List cargo(Long a){  
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select funcao.funcao from funcao,setor where setor.id = funcao.setor_id and setor.id=:likes order by funcao.funcao");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Setor selecionar(long i){
        em.getTransaction().begin();
        Setor s = em.find(Setor.class, i);
        em.getTransaction().commit();
        return s;
    }
    public List impressao(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select * from setor where setor.nome_setor NOT like '%DIVERSOS' and setor.nome_setor NOT like 'SGQ%' order by setor.nome_setor asc;",Setor.class);
        List<Setor> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
