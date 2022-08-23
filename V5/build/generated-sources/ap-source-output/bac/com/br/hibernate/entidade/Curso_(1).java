package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, String> escola;
	public static volatile SingularAttribute<Curso, String> grauInstrucao;
	public static volatile SingularAttribute<Curso, Date> dataTermino;
	public static volatile SingularAttribute<Curso, String> nome;
	public static volatile SingularAttribute<Curso, Long> id;
	public static volatile SingularAttribute<Curso, Date> dataInicio;
	public static volatile SingularAttribute<Curso, Colaborador> colaboradorId;

}

