/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Curso;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class CursoDao {
    private EntityManager em;
    public CursoDao(){
        em = Singleton.getconection();
    }
    public void inserir(Curso curso){
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
    }
    public void alterar(Curso curso){
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
    }
    public void excluir(Curso curso){
        em.getTransaction().begin();
        em.remove(curso);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select c from Curso c where c.nome like:likes");
        query.setParameter("likes","%"+texto+"%");
        List<Curso> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
}
