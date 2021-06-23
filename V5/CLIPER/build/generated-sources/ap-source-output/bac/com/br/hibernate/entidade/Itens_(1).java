package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Itens.class)
public abstract class Itens_ {

	public static volatile ListAttribute<Itens, Periodo> periodoList1;
	public static volatile SingularAttribute<Itens, String> mesInicio;
	public static volatile ListAttribute<Itens, Periodo> periodoList;
	public static volatile ListAttribute<Itens, Treinamento> treinamentoList;
	public static volatile SingularAttribute<Itens, Long> id;
	public static volatile SingularAttribute<Itens, String> mesFim;
	public static volatile SingularAttribute<Itens, Pgr> pgrId;
	public static volatile SingularAttribute<Itens, String> descricao;

}

