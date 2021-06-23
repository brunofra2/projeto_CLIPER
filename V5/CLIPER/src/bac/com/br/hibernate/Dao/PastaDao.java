/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Pasta;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.utils.Singleton;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class PastaDao {
    private EntityManager em;
    public PastaDao(){
        em = Singleton.getconection();
    }
    public void inserir(Pasta pa){
        em.getTransaction().begin();
        em.persist(pa);
        em.getTransaction().commit();
    }
    public void alterar(Pasta pa){
        em.getTransaction().begin();
        em.merge(pa);
        em.getTransaction().commit();
    }
    public void excluir(Pasta pa){
        em.getTransaction().begin();
        em.remove(pa);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select e from Pasta e where e.numero like:likes");
        query.setParameter("likes", "%"+texto+"%");
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List atualizar(){
        em.getTransaction().begin();
        Query query = em.createQuery("select e from Pasta e");
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List treinamentos_pasta(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Treinamento t, Pasta d where t.pastaId = d.id and d.id =:a");
        query.setParameter("a", a);
        List<Treinamento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    
    public List treinamentos(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Treinamento t, Pasta p where t.pasta.id = p.id and p.id=:a");
        query.setParameter("a", a);
        List<Treinamento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
        
    }
    public List buscar(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select ifnull(max(pasta.id),0) as ID from pasta");
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List cont(String a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select COUNT(*) from treinamento as t WHERE t.pasta_id =:likes");
        query.setParameter("likes", a);
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Pasta str(String a){
        em.getTransaction().begin();
        Pasta pasta = new Pasta(Long.parseLong("-1"));
        Query query = em.createNativeQuery("select pasta.armazenamento from pasta as p where p.id =:likes");
        query.setParameter("likes", a);
        List<Pasta> lista = query.getResultList();
        if(lista.size() > 0){
            pasta = lista.get(0);
        }
        em.getTransaction().commit();
        return pasta;
    }
    public List dataDescarte(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DATE_ADD(CURDATE(),INTERVAL 1825 DAY)");
        List<Date> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Pasta seleciona(long id){
        em.getTransaction().begin();
        Pasta p = em.find(Pasta.class, id);
        em.getTransaction().commit();
        return p;
    }
    public List max(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select MAX(pasta.id) from pasta");
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getdescarte(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Pasta t where t.id=:a");
        query.setParameter("a", a);
        List<Pasta> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
        
    }
}
