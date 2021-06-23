package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Risco.class)
public abstract class Risco_ {

	public static volatile SingularAttribute<Risco, Funcao> idCargo;
	public static volatile SingularAttribute<Risco, String> fatorDeRisco;
	public static volatile SingularAttribute<Risco, String> intencidade;
	public static volatile SingularAttribute<Risco, String> categoria;
	public static volatile SingularAttribute<Risco, Integer> idRisco;
	public static volatile SingularAttribute<Risco, String> classificacao;

}

