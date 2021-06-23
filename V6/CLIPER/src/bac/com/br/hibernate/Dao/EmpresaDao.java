/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Empresa;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class EmpresaDao {
    private EntityManager em;
    public EmpresaDao(){
        em = Singleton.getconection();
    }
    public void inserir(Empresa emp){
        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
    }
    public void alterar(Empresa emp){
        em.getTransaction().begin();
        em.merge(emp);
        em.getTransaction().commit();
    }
    public void excluir(Empresa emp){
        em.getTransaction().begin();
        em.remove(emp);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select e from Empresa e where e.nome like:likes order by e.nome");
        query.setParameter("likes","%"+texto+"%");
        List<Empresa> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
        
    }
}
