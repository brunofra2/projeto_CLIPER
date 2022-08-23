package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoTreinamento.class)
public abstract class TipoTreinamento_ {

	public static volatile SingularAttribute<TipoTreinamento, String> tipoTreinamento;
	public static volatile ListAttribute<TipoTreinamento, Documento> documentoList;
	public static volatile SingularAttribute<TipoTreinamento, Integer> periodo;
	public static volatile ListAttribute<TipoTreinamento, Treinamento> treinamentoList;
	public static volatile SingularAttribute<TipoTreinamento, Long> id;

}

