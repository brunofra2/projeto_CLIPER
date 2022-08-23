package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Documento.class)
public abstract class Documento_ {

	public static volatile SingularAttribute<Documento, String> modificacao;
	public static volatile SingularAttribute<Documento, String> tipo;
	public static volatile SingularAttribute<Documento, String> titulo;
	public static volatile ListAttribute<Documento, Treinamento> treinamentoList;
	public static volatile SingularAttribute<Documento, Long> id;
	public static volatile SingularAttribute<Documento, TipoTreinamento> tipoTreinamentoId;
	public static volatile ListAttribute<Documento, Descricao> descricaoList;
	public static volatile SingularAttribute<Documento, Setor> setorId;

}

