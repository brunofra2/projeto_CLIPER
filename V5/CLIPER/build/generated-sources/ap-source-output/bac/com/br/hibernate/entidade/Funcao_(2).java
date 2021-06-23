package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcao.class)
public abstract class Funcao_ {

	public static volatile SingularAttribute<Funcao, String> funcao;
	public static volatile SingularAttribute<Funcao, String> condicao;
	public static volatile SingularAttribute<Funcao, Domicilio> domicilioId;
	public static volatile ListAttribute<Funcao, Historico> historicoList;
	public static volatile ListAttribute<Funcao, ItemSeguranca> itemSegurancaList;
	public static volatile ListAttribute<Funcao, Colaborador> colaboradorList;
	public static volatile SingularAttribute<Funcao, Lotacao> lotacaoId;
	public static volatile ListAttribute<Funcao, Atividades> atividadesList;
	public static volatile ListAttribute<Funcao, Risco> riscoList;
	public static volatile SingularAttribute<Funcao, Long> id;
	public static volatile SingularAttribute<Funcao, Setor> setorId;
	public static volatile ListAttribute<Funcao, Descricao> descricaoList;

}

