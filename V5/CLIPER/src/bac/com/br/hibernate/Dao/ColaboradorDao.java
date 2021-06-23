/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Curso;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Historico;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class ColaboradorDao {

    private EntityManager em;

    public ColaboradorDao() {
        em = Singleton.getconection();
    }

    public void inserir(Colaborador col) {
        em.getTransaction().begin();
        em.persist(col);
        em.getTransaction().commit();
    }

    public void alterar(Colaborador col) {
        em.getTransaction().begin();
        em.merge(col);
        em.getTransaction().commit();
    }

    public void excluir(Colaborador col) {
        em.getTransaction().begin();
        em.remove(col);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("SELECT * FROM colaborador as e WHERE e.nome like:nome order by e.registro",Colaborador.class);
        query.setParameter("nome","%"+texto+"%");
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
}
    public List ativos_inativos(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select e from Colaborador e where e.status like:likes order by e.registro");
        query.setParameter("likes",texto);
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getregistro(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select e from Colaborador e where e.registro like:likes order by e.nome");
        query.setParameter("likes","%"+texto+"%");
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getsetor(String texto){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("SELECT colaborador.`*` from colaborador,funcao,setor WHERE colaborador.funcao_id = funcao.id\n" +
"AND funcao.setor_id = setor.id and colaborador.status = 'ATIVO' AND setor.nome_setor =:likes order by colaborador.registro",Colaborador.class);
        query.setParameter("likes",texto);
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Colaborador adiciona_colaborador(long i){
        em.getTransaction().begin();
        Colaborador c = em.find(Colaborador.class, i);
        em.getTransaction().commit();
        return c;
    }
    public List cursos(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Historico t,Colaborador d where t.idColaborador.id = d.id and d.id =:likes");
        query.setParameter("likes",a);
        List<Historico> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List documentos(Long a){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Documento t,Colaborador c, Treinamento a where t.id = a.documento.id and a.colaborador.id = c.id and c.id =:likes order by t.titulo");
        query.setParameter("likes",a);
        List<Documento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List titulo_documento(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select distinct d.titulo from treinamento as t, treinamento_documento as td, documento as d, treinamento_colaborador as tc, colaborador as c where t.`status` = 'ATUALIZADO' and t.id = td.Treinamento_id and td.documento_id = d.id and t.id = tc.Treinamento_id and tc.colaborador_id = c.id and c.id =:likes order by d.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List tipo_documento(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select d.tipo from treinamento as t, treinamento_documento as td, documento as d, treinamento_colaborador as tc, colaborador as c where t.`status` = 'ATUALIZADO' and t.id = td.Treinamento_id and td.documento_id = d.id and t.id = tc.Treinamento_id and tc.colaborador_id = c.id and c.id =:likes order by d.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List grafico(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinamento_colaborador.colaborador_id \n" +
"from colaborador inner join treinamento_colaborador \n" +
"on colaborador.id = treinamento_colaborador.colaborador_id and colaborador.`status` like 'ATIVO'\n" +
"inner JOIN treinamento ON treinamento.id = treinamento_colaborador.Treinamento_id \n" +
"and treinamento.`status` like 'ATUALIZADO' GROUP by colaborador.id");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public BigInteger estatus(String s){
        em.getTransaction().begin();
        BigInteger b = null;
        Query query = em.createNativeQuery("select colaborador.id from colaborador,treinamento,treinamento_colaborador where colaborador.id = treinamento_colaborador.colaborador_id and treinamento_colaborador.Treinamento_id = treinamento.id and treinamento.`status` like 'ATUALIZADO' and colaborador.id =:likes");
        query.setParameter("likes", s);
        List<BigInteger> lista = query.getResultList();
        if(lista.size() > 0){
            b = lista.get(0);
        }
        em.getTransaction().commit();
        return b;
    }
    
    public List colaboradores_nulos(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinamento_colaborador.colaborador_id from colaborador LEFT OUTER join treinamento_colaborador on colaborador.id = treinamento_colaborador.colaborador_id GROUP by colaborador.nome");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List colaboradores_nulos2(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select colaborador.nome from colaborador LEFT OUTER join treinamento_colaborador on colaborador.id = treinamento_colaborador.colaborador_id GROUP by colaborador.nome");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Colaborador seleciona(Long id){
        em.getTransaction().begin();
        Colaborador col = em.find(Colaborador.class, id);
        em.getTransaction().commit();
        return col;
    }
}
