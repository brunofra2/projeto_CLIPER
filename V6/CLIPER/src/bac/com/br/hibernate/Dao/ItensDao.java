/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Itens;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager; 
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class ItensDao {
    private EntityManager em;
    
    public ItensDao(){
        em = Singleton.getconection();
    }
    public void inserir(Itens i){
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
    }
    public void alterar(Itens i){
        em.getTransaction().begin();
        em.merge(i);
        em.getTransaction().commit();
    }
    public void excluir(Itens i){
        em.getTransaction().begin();
        em.remove(i);
        em.getTransaction().commit();
    }
    public List getlista(String s){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Itens t where t.descricao like:likes");
        query.setParameter("likes", s);
        List<Itens> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List itens(String a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Itens t,Pgr d where t.pgrId = d.id and d.ano =:likes");
        query.setParameter("likes",a);
        List<Itens> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List itenscombo(String a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Itens t,Pgr d where t.pgrId = d.id and d.ano =:likes");
        query.setParameter("likes",a);
        List<Itens> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Itens selecionar(long id){
        em.getTransaction().begin();
        Itens t = em.find(Itens.class, id);
        em.getTransaction().commit();
        return t;
    }
    
    public BigInteger selecionariten(String nome,long id){
        System.out.println("item selecionado");
        em.getTransaction().begin();
        BigInteger i = null;
        Query query = em.createNativeQuery("select itens.id from itens,pgr WHERE pgr.id = itens.pgr_id and itens.pgr_id =:id and itens.descricao like:nome");
        query.setParameter("id", id);
        query.setParameter("nome", nome);
        System.out.println("nome"+nome);
        System.out.println("id_combo"+id);
        List<BigInteger> lista = query.getResultList();
        if(lista.size() > 0){
            i = lista.get(0);
        }
        System.out.println("tamanho"+lista.size());
        System.out.println("id item"+i);
        em.getTransaction().commit();
        return i;
    }
    public BigInteger selecionarano(String nome){
        System.out.println("ano selecionado");
        em.getTransaction().begin();
        BigInteger i = null;
        Query query = em.createNativeQuery("select pgr.id from pgr where pgr.ano like:nome");
        query.setParameter("nome", nome);
        System.out.println("nome"+nome);
        List<BigInteger> lista = query.getResultList();
        if(lista.size() > 0){
            i = lista.get(0);
        }
        System.out.println("tamanho"+lista.size());
        System.out.println("id item"+i);
        em.getTransaction().commit();
        return i;
    }
}
