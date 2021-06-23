package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lotacao.class)
public abstract class Lotacao_ {

	public static volatile SingularAttribute<Lotacao, String> titulo;
	public static volatile ListAttribute<Lotacao, Funcao> funcaoList;
	public static volatile SingularAttribute<Lotacao, Integer> idLocacao;
	public static volatile SingularAttribute<Lotacao, Domicilio> idDomicilio;

}

