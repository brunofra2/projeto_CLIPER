package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Atividades;
import bac.com.br.hibernate.entidade.Avaliacao;
import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Funcao;
import bac.com.br.hibernate.utils.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AvaliacaoDao {

    EntityManager em;
    public AvaliacaoDao(){
        em = Singleton.getconection();
    }

    public void incluir(Avaliacao ava){
        em.getTransaction().begin();
        em.persist(ava);
        em.getTransaction().commit();
    }

    public void excluir(Avaliacao ava){
        em.getTransaction().begin();
        em.remove(ava);
        em.getTransaction().commit();
    }

    public void alterar(Avaliacao ava){
        em.getTransaction().begin();
        em.merge(ava);
        em.getTransaction().commit();
    }
    public List getcargo(){
        em.getTransaction().begin();
        //Query query = em.createQuery("SELECT a from Atividades a where a.idCargo.funcao =:cargo");
        //query.setParameter("cargo", "%"+texto+"%");
        Query query = em.createNativeQuery("select * from funcao", Funcao.class);
        List<Funcao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }

    public List getavaliaca(Long id){
        em.getTransaction().begin();
        //Query query = em.createQuery("SELECT a from Atividades a where a.idCargo.funcao =:cargo");
        //query.setParameter("cargo", "%"+texto+"%");
        Query query = em.createNativeQuery("select * from avaliacao where avaliacao.id_avaliacao =:id", Avaliacao.class);
        query.setParameter("id",id);
        List<Avaliacao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;

    }

    public List getcolaborador(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("SELECT * FROM colaborador as e WHERE e.nome like:nome order by e.registro", Colaborador.class);
        query.setParameter("nome", "%" + texto + "%");
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;

    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select * from avaliacao where avaliacao.condicao =:condicao", Avaliacao.class);
        query.setParameter("condicao", "%" + texto + "%");
        List<Avaliacao> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;

    }
}
