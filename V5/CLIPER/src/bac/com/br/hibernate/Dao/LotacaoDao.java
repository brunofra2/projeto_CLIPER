/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Domicilio;
import bac.com.br.hibernate.entidade.Lotacao;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class LotacaoDao {
        EntityManager em;
        
        public LotacaoDao(){
            em = Singleton.getconection();
        }
        
        public void insert(Lotacao lo){
            em.getTransaction().begin();
            em.persist(lo);
            em.getTransaction().commit();
        }
        public void alter(Lotacao lo){
            em.getTransaction().begin();
            em.merge(lo);
            em.getTransaction().commit();
        }
        public List getlista(String texto){
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from lotacao where lotacao.titulo like:texto",Lotacao.class);
            query.setParameter("texto","%"+texto+"%");
            List<Lotacao> lista = query.getResultList();
            em.getTransaction().commit();
            return lista;
        }
}
