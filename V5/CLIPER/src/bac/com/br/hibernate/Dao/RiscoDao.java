/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Risco;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class RiscoDao {
        EntityManager em;
        
        public RiscoDao(){
            em = Singleton.getconection();
        }
        
        public void insert(Risco ris){
            em.getTransaction().begin();
             em.persist(ris);
            em.getTransaction().commit();
        }
        public void alter(Risco ris){
            em.getTransaction().begin();
            em.merge(ris);
            em.getTransaction().commit();
        }
        
        public void excluir(Risco ris){
            em.getTransaction().begin();
            em.remove(ris);
            em.getTransaction().commit();
        }
        
        public List getlista(Long texto){
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from risco where risco.id_cargo =:id",Risco.class);
            query.setParameter("id", texto);
            List<Risco> lista = query.getResultList();
            em.getTransaction().commit();
            return lista;
        }
        
}
