package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pasta.class)
public abstract class Pasta_ {

	public static volatile SingularAttribute<Pasta, Integer> numero;
	public static volatile SingularAttribute<Pasta, String> armazenamento;
	public static volatile SingularAttribute<Pasta, Date> dataDescarte;
	public static volatile SingularAttribute<Pasta, String> descarte;
	public static volatile ListAttribute<Pasta, Treinamento> treinamentoList;
	public static volatile SingularAttribute<Pasta, Long> id;

}

