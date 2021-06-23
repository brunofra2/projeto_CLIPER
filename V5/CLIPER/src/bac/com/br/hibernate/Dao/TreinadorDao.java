/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Treinador;
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
public class TreinadorDao {
    private EntityManager em;
    public TreinadorDao(){
        em = Singleton.getconection();
    }
    public void inserir(Treinador tre){
        em.getTransaction().begin();
        em.persist(tre);
        em.getTransaction().commit();
    }
    public void alterar(Treinador tre){
        em.getTransaction().begin();
        em.merge(tre);
        em.getTransaction().commit();
    }
    public void excluir(Treinador tre){
        em.getTransaction().begin();
        em.remove(tre);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Treinador t where t.nome like:likes");
        query.setParameter("likes", "%"+texto+"%");
        List<Treinador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Treinador seleciona(long id){
        em.getTransaction().begin();
        Treinador t = em.find(Treinador.class, id);
        em.getTransaction().commit();
        return t;
    }
    public Treinamento seleciona_treinamento(long id){
        em.getTransaction().begin();
        Treinamento t = em.find(Treinamento.class, id);
        em.getTransaction().commit();
        return t;
    }
}
