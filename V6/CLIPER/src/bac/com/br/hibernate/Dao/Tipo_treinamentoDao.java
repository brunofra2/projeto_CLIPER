/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.TipoTreinamento;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class Tipo_treinamentoDao {
        private EntityManager em; 
        
        public Tipo_treinamentoDao(){
            em = Singleton.getconection();
        }
        
        public void inserir(TipoTreinamento ti){
            em.getTransaction().begin();
            em.persist(ti);
            em.getTransaction().commit();
        }
        public void alterar(TipoTreinamento ti){
            em.getTransaction().begin();
            em.merge(ti);
            em.getTransaction().commit();
        }
        public void excluir(TipoTreinamento ti){
           em.getTransaction().begin();
           em.remove(ti);
           em.getTransaction().commit();
        }
        public TipoTreinamento seleciona(int i){
            em.getTransaction().begin();
            TipoTreinamento ti = em.find(TipoTreinamento.class, i);
            em.getTransaction().commit();
            return ti;
            
        }
        public List combo(){
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM TipoTreinamento e");
            List<TipoTreinamento>  lista = query.getResultList();
            em.getTransaction().commit();
            return lista;
            
        }
        public List getlista(String texto){
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT e FROM TipoTreinamento e  WHERE e.tipoTreinamento LIKE:likes");
            query.setParameter("likes", "%"+texto+"%");
            List<TipoTreinamento> lista = query.getResultList();
            em.getTransaction().commit();
            return lista;
        }
}
