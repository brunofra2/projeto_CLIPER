/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Avaliacao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class AvaliacaoDao {
    EntityManager em;
    public  AvaliacaoDao(){
        em = Singleton.getconection();
    }
    public void incluir(Avaliacao av){
        em.getTransaction().begin();
        em.persist(av);
        em.getTransaction().commit();
    }
    public void alterar(Avaliacao ava){
        em.getTransaction().begin();
        em.merge(ava);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Avaliacao a where a.estatusAvaliacao LIKE:likes",Avaliacao.class);
        query.setParameter("likes", texto);
        List<Avaliacao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List getcolaborador(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select * from colaborador where colaborador.registro like:registro",Colaborador.class);
        query.setParameter("registro",texto);
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getcargo(){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Funcao a where a.condicao like:likes");
        query.setParameter("likes", "ATIVO");
        List<Funcao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getavaliaca(Long id){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("SELECT * FROM avaliacao WHERE avaliacao.id_colaborador =:likes AND avaliacao.estatus_avaliacao != 'REPROVADO'",Avaliacao.class);
        query.setParameter("likes", id);
        List<Avaliacao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }public Avaliacao seleciona(Long id){
        em.getTransaction().begin();
        Avaliacao col = em.find(Avaliacao.class, id);
        em.getTransaction().commit();
        return col;
    }
}
