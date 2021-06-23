/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class FuncaoDao {
    private EntityManager em;
    public FuncaoDao(){
        em = Singleton.getconection();
    }
    public void inserir(Funcao fun){
        em.getTransaction().begin();
        em.persist(fun);
        em.getTransaction().commit();
    }
    public void alterar(Funcao fun){
        em.getTransaction().begin();
        em.merge(fun);
        em.getTransaction().commit();
    }
    public void excluir(Funcao fun){
        em.getTransaction().begin();
        em.remove(fun);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select * from funcao where funcao.funcao like:likes  order by funcao.funcao",Funcao.class);
        query.setParameter("likes", "%"+texto+"%");
        List<Funcao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getlista2(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select * from funcao where funcao.funcao like:likes and funcao.condicao LIKE 'ATIVO' order by funcao.funcao",Funcao.class);
        query.setParameter("likes", "%"+texto+"%");
        List<Funcao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List colaboradores_cargo(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Colaborador t, Funcao d where t.funcaoId.id = d.id and d.id =:a and t.status like 'ATIVO' order by t.nome");
        query.setParameter("a", a);
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public Funcao seleciona(Long id){
        em.getTransaction().begin();
        Funcao fu = em.find(Funcao.class, id);
        em.getTransaction().commit();
        return fu;
    }
}
