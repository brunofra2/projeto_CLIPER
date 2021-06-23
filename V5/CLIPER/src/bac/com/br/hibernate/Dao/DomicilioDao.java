/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Domicilio;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class DomicilioDao {
        EntityManager em;
        public DomicilioDao(){
            em = Singleton.getconection();
        }
        
        public void insert(Domicilio dom){
            em.getTransaction().begin();
            em.persist(dom);
            em.getTransaction().commit();
        }
        public void alter(Domicilio dom){
            em.getTransaction().begin();
            em.merge(dom);
            em.getTransaction().commit();
        }
        public List getlista(String texto){
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from domicilio where domicilio.nome like:nome",Domicilio.class);
            query.setParameter("nome","%"+texto+"%");
            List<Domicilio> lista = query.getResultList();
            em.getTransaction().commit();
            return lista;
        }
}
