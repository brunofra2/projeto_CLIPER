/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bac.com.br.hibernate.Dao;

import bac.com.br.hibernate.entidade.Colaborador;
import bac.com.br.hibernate.entidade.Documento;
import bac.com.br.hibernate.entidade.Treinamento;
import bac.com.br.hibernate.utils.Meses;
import bac.com.br.hibernate.utils.Singleton;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuário
 */
    
public class TreinamentoDao {
    
    private EntityManager em;
    
    public TreinamentoDao(){
        em = Singleton.getconection();
    }
    public void inserir(Treinamento tre){
        em.getTransaction().begin();
        em.persist(tre);
        em.getTransaction().commit();
    }
    public void alterar(Treinamento tre){
        em.getTransaction().begin();
        em.merge(tre);
        em.getTransaction().commit();
    }
    
    public void excluir(Treinamento tre){
        em.getTransaction().begin();
        em.remove(tre);
        em.getTransaction().rollback();
    }
    public List getlista(String texto){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Treinamento t where t.tipo LIKE:likes ORDER BY t.data_treinamento");
        query.setParameter("likes", "%"+texto+"%");
        List<Treinamento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List participanteid(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.id from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List participantedata(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.data_treinamento from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<Date> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List participantetipo(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.tipo from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List participanteinicio(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.horario_inicio from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List participantefinal(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.horario_final from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List participantevenc(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select treinamento.prox_treinamento from treinamento,colaborador,treinamento_colaborador where treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<Date> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List participantepasta(String a){
        em.getTransaction().begin();
        System.out.println("\n"+a);
        Query query = em.createNativeQuery("select pasta.numero from treinamento,colaborador,treinamento_colaborador,pasta where treinamento.pasta_id = pasta.id and treinamento.id = treinamento_colaborador.Treinamento_id and treinamento_colaborador.colaborador_id = colaborador.id and colaborador.nome like:likes order by treinamento.data_treinamento");
        query.setParameter("likes",a);
        List<Date> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List atualizar(){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Treinamento t where t.status like 'ATUALIZADO'");
        List<Treinamento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getcolaborador(String nome){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT t FROM Colaborador t WHERE t.status like 'ATIVO' and t.nome LIKE:likes");
        query.setParameter("likes","%"+nome+"%");
        List<Colaborador> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getdocumento(){
        em.getTransaction().begin();
        Query query = em.createQuery("select t from Documento t");
        List<Documento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List getdocumento2(String nome){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select documento.* from documento,setor where documento.setor_id = setor.id and setor.nome_setor LIKE:likes and documento.modificacao like 'ATIVO' order by documento.titulo",Documento.class);
        query.setParameter("likes", nome);
        List<Documento> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List curdate(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select CURDATE()");
        List<Date> data = query.getResultList();
        em.getTransaction().commit();
        return data;
    }
    public String addcurdate(Date d, int id){
        em.getTransaction().begin();
        String a = null;
        Query query = em.createNativeQuery("SELECT DATE_ADD(:data,INTERVAL :meses MONTH)");
        query.setParameter("data", d);
        query.setParameter("meses", id);
        List<String> lista = query.getResultList();
        if(lista.size() > 0){
            a = String.valueOf(lista.get(0));
        }
        em.getTransaction().commit();
        return a;
    }
    public List registro(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select c.registro from treinamento as t, treinamento_colaborador as tc, colaborador as c where t.id = tc.Treinamento_id and c.id = tc.colaborador_id and t.id =:likes");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List titulo(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select d.titulo from treinamento as t, treinamento_documento as td, documento as d where t.id = td.Treinamento_id and d.id = td.documento_id and t.id=:likes order by d.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List id_documento(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select d.id from treinamento as t, treinamento_documento as td, documento as d where t.id = td.Treinamento_id and d.id = td.documento_id and t.id=:likes order by d.titulo");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List tipo(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select d.tipo from treinamento as t, treinamento_documento as td, documento as d where t.id = td.Treinamento_id and d.id = td.documento_id and t.id=:likes");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List nome(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select c.nome from treinamento as t, treinamento_colaborador as tc, colaborador as c where t.id = tc.Treinamento_id and c.id = tc.colaborador_id and t.id =:likes");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List id(Long a){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select c.id from treinamento as t, treinamento_colaborador as tc, colaborador as c where t.id = tc.Treinamento_id and c.id = tc.colaborador_id and t.id =:likes");
        query.setParameter("likes",a);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List grafico(String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select count(treinamento.id) from treinamento \n" +
"where EXTRACT(YEAR FROM treinamento.data_treinamento) =:ano\n" +
"GROUP BY MONTH(treinamento.data_treinamento) order BY  MONTH(treinamento.data_treinamento)");
        query.setParameter("ano", ano);
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List grafico2(String ano){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DISTINCT MONTH(treinamento.data_treinamento)  from treinamento \n" +
"where EXTRACT(YEAR FROM treinamento.data_treinamento) =:ano\n" +
"order BY MONTH(treinamento.data_treinamento) asc");
        query.setParameter("ano", ano);
        List<Object> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List min(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select min(EXTRACT(YEAR FROM treinamento.data_treinamento)) from treinamento");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List max(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select max(EXTRACT(YEAR FROM treinamento.data_treinamento)) from treinamento");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List min2(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select min(EXTRACT(YEAR FROM treinamento.data_treinamento)) from treinamento");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    public List max2(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select max(EXTRACT(YEAR FROM treinamento.data_treinamento)) from treinamento");
        List<String> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List validade(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinamento.data_treinamento from treinamento");
        List<Date> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public List ids(){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select treinamento.id from treinamento");
        List<BigInteger> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
    public BigInteger validade(Date d){
        em.getTransaction().begin();
        BigInteger big = null;
        Query query = em.createNativeQuery("select DATEDIFF(CURDATE(),:data)");
        query.setParameter("data", d);
        List<BigInteger> lista = query.getResultList();
        if(lista.size() > 0){
            big = lista.get(0);
        }
        em.getTransaction().commit();
        return big;
    }
    
    public List DATEDIFF(Long id){
        em.getTransaction().begin();
        Query query = em.createNativeQuery("select DATEDIFF(CURDATE(),treinamento.prox_treinamento) as diferenca from treinamento where treinamento.id =:id");
        query.setParameter("id", id);
        List<BigInteger> data = query.getResultList();
        em.getTransaction().commit();
        return data;
    }
    public List limite(Long id,String data){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Treinamento as a WHERE datediff(:data,a.prox_treinamento) < 90 and a.id =:id");
        query.setParameter("data",data);
        query.setParameter("id", id);
        List<Integer> datas = query.getResultList();
        em.getTransaction().commit();
        return datas;
    }
    
    public List prorrogavel(Long id,String data){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Treinamento as a WHERE datediff(:data,a.prox_treinamento) > 0 and a.id =:id");
        query.setParameter("data",data);
        query.setParameter("id", id);
        List<Integer> datas = query.getResultList();
        em.getTransaction().commit();
        return datas;
    }
    
     public List prorrogavel(Long id){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Treinamento as a WHERE datediff(CURRENT_DATE(),a.prox_treinamento) < 30 and datediff(CURRENT_DATE(),a.prox_treinamento) > 0 and a.id =:id");
        query.setParameter("id", id);
        List<Integer> datas = query.getResultList();
        em.getTransaction().commit();
        return datas;
    }
     
     
    // alteração
     
     public String pegar_ano(Long id_item, Long id){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select pgr.ano from treinamento, itens, pgr \n" +
"	where \n" +
"	treinamento.itens_id = itens.id\n" +
"	and itens.pgr_id = pgr.id\n" +
"	and itens.id =:id_item \n" +
"	and treinamento.id =:id");
         query.setParameter("id_item", id_item);
         query.setParameter("id", id);
         String ano = String.valueOf(query.getSingleResult());
         em.getTransaction().commit();
         return ano;
     }
     
     //ABAIXO FUNÇÕES PARA RECUPERAR INFORMAÇÕES DOS TREINAMENTOS PARA ALTERAÇÃO
     
     public List relacao_ManytoMany(Treinamento trei){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select treinamento_documento.documento_id from treinamento_documento,treinamento where\n" +
"treinamento_documento.Treinamento_id = treinamento.id and treinamento.id =:id");
         query.setParameter("id", trei.getId());
         List<String> lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     public void drop_relacao(){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("delete treinamento_documento from treinamento_documento,treinamento where\n" +
"treinamento_documento.Treinamento_id = treinamento.id and treinamento_documento.documento_id =25 and treinamento.id =971");
         
         em.getTransaction().commit();
     }
     
     public List listar_colaboradores(int id){
         List<Colaborador> lista =null;
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select colaborador.nome,colaborador.registro from colaborador, treinamento, treinamento_colaborador\n" +
"where colaborador.id = treinamento_colaborador.colaborador_id and treinamento.id = treinamento_colaborador.Treinamento_id\n" +
"and treinamento.id =:id");
         query.setParameter("id", id);
         lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     
     public List listar_documentos(int id){
         List<Documento> lista =null;
         em.getTransaction().begin();
         Query query =em.createNativeQuery("select documento.titulo from documento,treinamento_documento,treinamento\n" +
"where documento.id = treinamento_documento.documento_id and treinamento_documento.Treinamento_id = treinamento.id\n" +
"and treinamento.id =:id");
         query.setParameter("id", id);
         lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     
     public String recuperar_treinador(int id){
         String treinador = null;
         em.getTransaction().begin();
         Query query =em.createNativeQuery("select treinador.nome,treinador.formacao from treinador,treinamento\n" +
"where treinador.id = treinamento.treinador_id and treinamento.id=:id");
         query.setParameter("id", id);
         treinador = String.valueOf(query.getSingleResult());
         em.getTransaction().commit();
         return treinador;
     }
     
     public String recuperar_tipo(int id){
         String a = "";
         em.getTransaction().begin();
         Query query =em.createNativeQuery("select tipo_treinamento.tipo_treinamento from tipo_treinamento,treinamento \n" +
"where tipo_treinamento.id = treinamento.tipo_treinamento_id and treinamento.id=:id");
         query.setParameter("id", id);
         a = String.valueOf(query.getSingleResult());
         em.getTransaction().commit();
         return a;
     }
     
     public List recuperar_cronograma(int id){
         List lista= null;
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select pgr.* from itens,pgr,treinamento \n" +
"where pgr.id = itens.pgr_id and itens.id = treinamento.itens_id and treinamento.id=:id");
         query.setParameter("id", id);
         lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     
     public List recuperar(int id){
         List<Treinamento> lista = null;
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select * from treinamento where treinamento.id=:id");
         query.setParameter("id", id);
         lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     public List Quantidade_colaborador_nome(){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select setor.nome_setor from colaborador,funcao,setor\n" +
                        "where setor.id = funcao.setor_id \n" +
                        "and funcao.id = colaborador.funcao_id \n" +
                        "and colaborador.`status` like 'ATIVO'\n" +
                        "GROUP BY setor.id;");
         List<String> lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     public List Quantidade_colaborador_id(){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select COUNT(colaborador.id) from colaborador,funcao,setor\n" +
                    "where setor.id = funcao.setor_id \n" +
                    "and funcao.id = colaborador.funcao_id \n" +
                    "and colaborador.`status` like 'ATIVO'\n" +
                    "GROUP BY setor.id;");
         List<BigInteger> lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
     public List quantidade_treinados_nome(String ano){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select setor.nome_setor from colaborador,funcao,setor,treinamento, treinamento_colaborador\n" +
                    "where setor.id = funcao.setor_id \n" +
                    "and funcao.id = colaborador.funcao_id \n" +
                    "and colaborador.`status` like 'ATIVO'\n" +
                    "AND treinamento.tipo LIKE 'IT - Instrução de Trabalho'\n"+
                    "and colaborador.id = treinamento_colaborador.colaborador_id\n" +
                    "and treinamento_colaborador.Treinamento_id = treinamento.id\n" +
                    "and YEAR(treinamento.data_treinamento) =:ano\n" +
                    "GROUP BY setor.id;");
         query.setParameter("ano",ano);
         List<String> obj = query.getResultList();
         em.getTransaction().commit();
         return obj;
     }
     public List quantidade_treinados_id(String ano){
         em.getTransaction().begin();
         Query query = em.createNativeQuery("select COUNT(DISTINCT colaborador.id) from colaborador,funcao,setor,treinamento, treinamento_colaborador\n" +
                    "where setor.id = funcao.setor_id \n" +
                    "and funcao.id = colaborador.funcao_id \n" +
                    "and colaborador.`status` like 'ATIVO'\n" +
                    "AND treinamento.tipo LIKE 'IT - Instrução de Trabalho' \n"+
                    "and colaborador.id = treinamento_colaborador.colaborador_id\n" +
                    "and treinamento_colaborador.Treinamento_id = treinamento.id\n" +
                    "and YEAR(treinamento.data_treinamento) =:ano\n" +
                    "GROUP BY setor.id;");
         query.setParameter("ano", ano);
         List<BigInteger> lista = query.getResultList();
         em.getTransaction().commit();
         return lista;
     }
}
