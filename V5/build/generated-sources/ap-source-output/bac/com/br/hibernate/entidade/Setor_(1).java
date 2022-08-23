package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Setor.class)
public abstract class Setor_ {

	public static volatile SingularAttribute<Setor, String> nomeSetor;
	public static volatile ListAttribute<Setor, Treinador> treinadorList;
	public static volatile SingularAttribute<Setor, String> situacao;
	public static volatile ListAttribute<Setor, Documento> documentoList;
	public static volatile ListAttribute<Setor, Funcao> funcaoList;
	public static volatile SingularAttribute<Setor, Long> id;
	public static volatile ListAttribute<Setor, Descricao> descricaoList;
	public static volatile SingularAttribute<Setor, String> responsavel;
	public static volatile SingularAttribute<Setor, String> email;

}

