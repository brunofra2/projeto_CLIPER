/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Pgr;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class PgrDao {
    private EntityManager em;
    
    public PgrDao(){
        em = Singleton.getconection();
    }
    public void inserir(Pgr p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    public void alterar(Pgr p){
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }
    public void excluir(Pgr p){
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }
    public List getlista(String s){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Pgr t where t.ano like:likes order by t.ano");
        query.setParameter("likes","%"+s+"%");
        List<Pgr> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List combo(String s){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Pgr t where t.ano like:likes");
        query.setParameter("likes","%"+s+"%");
        List<Pgr> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
