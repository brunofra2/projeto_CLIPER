/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Backup;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class BackupDao {
    EntityManager em;
    public BackupDao(){
        em =Singleton.getconection();
    }
    public void inserir(Backup bac){
        em.getTransaction().begin();
        em.persist(bac);
        em.getTransaction().commit();
    }
    public void alterar(Backup bac){
        em.getTransaction().begin();
        em.persist(bac);
        em.getTransaction().commit();
    }
    public void excluir(Backup bac){
        em.getTransaction().begin();
        em.remove(bac);
        em.getTransaction().commit();
    }
    public Integer ultimo_backup(){
        em.getTransaction().begin();
        Integer s = null;
        Query query = em.createQuery("SELECT MAX(e) from backup e ");
        List<Backup> lista = query.getResultList();
        if(lista.size() > 0){
            if(lista.get(0) == null){
                s = 0;
            }else{
            s = Integer.parseInt(lista.get(0).getId().toString());
            }
        }
        em.getTransaction().commit();
        return s;
    }
    public String diferenca_dias(Integer s){
        em.getTransaction().begin();
        String b= null;
        Query query = em.createNativeQuery("select DATEDIFF(CURDATE(),a.data_backup) from backup as a where a.id =:s");
        query.setParameter("s", s);
        List<Object> lista = query.getResultList();
        if(lista.size() > 0){
        b = String.valueOf(lista.get(0).toString());
        }
        em.getTransaction().commit();
        return b;
    }
}
