package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Colaborador.class)
public abstract class Colaborador_ {

	public static volatile SingularAttribute<Colaborador, String> estado;
	public static volatile SingularAttribute<Colaborador, Date> dataAdm;
	public static volatile ListAttribute<Colaborador, Historico> historicoList;
	public static volatile SingularAttribute<Colaborador, String> nome;
	public static volatile ListAttribute<Colaborador, Treinamento> treinamentoList;
	public static volatile SingularAttribute<Colaborador, Funcao> funcaoId;
	public static volatile SingularAttribute<Colaborador, String> registro;
	public static volatile SingularAttribute<Colaborador, Date> dataNasc;
	public static volatile SingularAttribute<Colaborador, String> serie;
	public static volatile SingularAttribute<Colaborador, String> ctps;
	public static volatile SingularAttribute<Colaborador, Long> id;
	public static volatile SingularAttribute<Colaborador, String> pis;
	public static volatile SingularAttribute<Colaborador, String> sexo;
	public static volatile ListAttribute<Colaborador, Curso> cursoList;
	public static volatile SingularAttribute<Colaborador, String> status;

}

