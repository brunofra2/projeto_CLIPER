/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.ItemSeguranca;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class EpiDao {
        EntityManager em;
    public EpiDao(){
        em = Singleton.getconection();
    }
    
    public void insert(ItemSeguranca seg){
        em.getTransaction().begin();
        em.persist(seg);
        em.getTransaction().commit();
    }
   public void alter(ItemSeguranca seg){
       em.getTransaction().begin();
       em.merge(seg);
       em.getTransaction().commit();
   }
   
   public void excluir(ItemSeguranca seg){
       em.getTransaction().begin();
       em.remove(seg);
       em.getTransaction().commit();
   }
   
   public List getlista(Long texto){
       em.getTransaction().begin();
       Query query = em.createNativeQuery("select * from item_seguranca where item_seguranca.id_cargo =:id",ItemSeguranca.class);
       query.setParameter("id", texto);
       List<ItemSeguranca> lista = query.getResultList();
       em.getTransaction().commit();
       return  lista;
       
   }
}
