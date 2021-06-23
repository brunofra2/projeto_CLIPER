package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Historico.class)
public abstract class Historico_ {

	public static volatile SingularAttribute<Historico, Funcao> idCargo;
	public static volatile SingularAttribute<Historico, Date> periodoInicio;
	public static volatile SingularAttribute<Historico, Date> periodoFim;
	public static volatile SingularAttribute<Historico, Colaborador> idColaborador;
	public static volatile SingularAttribute<Historico, Descricao> idDescricao;
	public static volatile SingularAttribute<Historico, Long> idHistorico;

}

