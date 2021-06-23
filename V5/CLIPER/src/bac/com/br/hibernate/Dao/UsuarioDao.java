/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Usuario;
import bac.com.br.hibernate.utils.Msg;
import bac.com.br.hibernate.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
public class UsuarioDao {
    private EntityManager em;
    public UsuarioDao(){
        em = Singleton.getconection();
    }
    public void inserir(Usuario us){
        em.getTransaction().begin();
        em.persist(us);
        em.getTransaction().commit();
    }
    public void alterar(Usuario us){
        em.getTransaction().begin();
        em.merge(us);
        em.getTransaction().commit();
    }
    public void excluir(Usuario us){
        em.getTransaction().begin();
        em.remove(us);
        em.getTransaction().commit();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select u from Usuario u where u.codigo like:likes");
        query.setParameter("likes","%"+texto+"%");
        List<Usuario> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public Usuario login(String codigo, String senha){
        em.getTransaction().begin();
        Usuario us = new Usuario(Long.parseLong("-1"));
        try {
        Query query = em.createNativeQuery("select * from usuario as t where t.codigo =:codigo and t.senha=:senha",Usuario.class);
        query.setParameter("codigo",codigo);
        query.setParameter("senha",senha);
        List<Usuario> lista = query.getResultList();
        if(lista.size() > 0){
            us = lista.get(0);
        }
        } catch (Exception e) {
            e.printStackTrace();
            Msg.erro(null, "erro "+e.getMessage());
        }
        em.getTransaction().commit();
        return us;
    }
    public Usuario seleciona(long i){
        em.getTransaction().begin();
        Usuario c = em.find(Usuario.class, i);
        em.getTransaction().commit();
        return c;
    }
    public boolean verifica_usuario(String codigo, Long id){
        em.getTransaction().begin();
        boolean vf = false;
        int i = 0;
        Query query = em.createNativeQuery("select usuario.id from usuario, treinador where usuario.id = treinador.usuario_id and usuario.codigo like:codigo and usuario.id =:id");
        query.setParameter("codigo", codigo);
        query.setParameter("id", id);
        List<String> lista = query.getResultList();
        if(lista.isEmpty()){
            vf = true;
        }
        em.getTransaction().commit();
        return vf;
    }   
    public List prorrogar(String codigo, String id){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinador.nome,treinador.email, treinador.email_supervisor from treinador, usuario where usuario.id = treinador.usuario_id and usuario.id =:id and usuario.codigo =:codigo");
        query.setParameter("id", id);
        query.setParameter("codigo", codigo);
        List<Object[]> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
