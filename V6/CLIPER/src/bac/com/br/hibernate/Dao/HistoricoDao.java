/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.utils.Singleton;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class HistoricoDao {
    private EntityManager em;
    public HistoricoDao(){
        em = Singleton.getconection();
    }
    
    public void persist(Historico his){
        em.getTransaction().begin();
        em.persist(his);
        em.getTransaction().commit();
    }
    public void update(Historico his){
        em.getTransaction().begin();
        em.merge(his);
        em.getTransaction().commit();
    }
    
    public Boolean excluir(Historico his){
        Boolean ex = false;
        try {
            em.getTransaction().begin();
            em.remove(his);
            em.getTransaction().commit();
            ex = true;
        } catch (Exception e) {
            em.getTransaction().begin();
            em.getTransaction().rollback();
            ex = false;
        }
        return ex;
    }
    public List curdate(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select curdate()");
        List<Date> s =query.getResultList();
        em.getTransaction().commit();
        return s;
    }
    public Historico seleciona(Long id){
        em.getTransaction().begin();
        Historico h = em.find(Historico.class, id);
        em.getTransaction().commit();
        return h;
    }
    
}
