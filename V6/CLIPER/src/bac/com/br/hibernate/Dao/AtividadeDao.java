/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Atividades;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class AtividadeDao {
    EntityManager em;
    public AtividadeDao(){
        em = Singleton.getconection();
    }
    
    public void insert(Atividades ati){
        em.getTransaction().begin();
        em.persist(ati);
        em.getTransaction().commit();
    }
    public void alter(Atividades ati){
        em.getTransaction().begin();
        em.merge(ati);
        em.getTransaction().commit();
    }
    
    public void excluir(Atividades ati){
        em.getTransaction().begin();
        em.remove(ati);
        em.getTransaction().commit();
    }
    public List getlista(Long id){
        em.getTransaction().begin();
        //Query query = em.createQuery("SELECT a from Atividades a where a.idCargo.funcao =:cargo");
        //query.setParameter("cargo", "%"+texto+"%");
        Query query = em.createNativeQuery("select * from atividades where atividades.id_cargo =:id",Atividades.class);
        query.setParameter("id", id);
        List<Atividades> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List get(String id){
        em.getTransaction().begin();
        //Query query = em.createQuery("SELECT a from Atividades a where a.idCargo.funcao =:cargo");
        //query.setParameter("cargo", "%"+texto+"%");
        
        //Query query = em.createNativeQuery("select atividades.* from atividades, funcao  where atividades.id_cargo = funcao.id AND funcao.funcao =:id",Atividades.class);
        Query query = em.createNativeQuery("select atividades.* from atividades, funcao  where atividades.id_cargo = funcao.id AND funcao.id =:id ORDER BY funcao.funcao",Atividades.class);
        query.setParameter("id", id);
        List<Atividades> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
