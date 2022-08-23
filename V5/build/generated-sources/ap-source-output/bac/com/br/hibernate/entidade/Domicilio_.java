package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Domicilio.class)
public abstract class Domicilio_ {

	public static volatile SingularAttribute<Domicilio, String> cnae;
	public static volatile ListAttribute<Domicilio, Lotacao> lotacaoList;
	public static volatile SingularAttribute<Domicilio, String> nome;
	public static volatile ListAttribute<Domicilio, Funcao> funcaoList;
	public static volatile SingularAttribute<Domicilio, String> cnpj;
	public static volatile SingularAttribute<Domicilio, Integer> idDomicilio;

}

