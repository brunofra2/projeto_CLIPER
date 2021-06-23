/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Periodo;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class PeriodoDao {

    EntityManager em;

    public PeriodoDao() {
        em = Singleton.getconection();
    }

    public List getcolaborador() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT t FROM periodo t");
        List<Periodo> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }

    public Periodo adiciona_meses(long i) {
        em.getTransaction().begin();
        Periodo c = em.find(Periodo.class, i);
        em.getTransaction().commit();
        return c;
    }

    public List cronograma(Long o) {
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select periodo.periodo from itens,pgr,periodo,itens_periodo where itens.id = itens_periodo.Itens_id and periodo.id = itens_periodo.periodo_id and pgr.id = itens.pgr_id and pgr.id =:id");
        query.setParameter("id", o);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }

    public List cronogramade(Long o) {
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select itens.descricao from itens,pgr,periodo,itens_periodo where itens.id = itens_periodo.Itens_id and periodo.id = itens_periodo.periodo_id and pgr.id = itens.pgr_id and pgr.id =:id group by itens.descricao");
        query.setParameter("id", o);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }

}
