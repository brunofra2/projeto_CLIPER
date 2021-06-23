package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Treinamento.class)
public abstract class Treinamento_ {

	public static volatile SingularAttribute<Treinamento, Pasta> pastaId;
	public static volatile SingularAttribute<Treinamento, String> horarioInicio;
	public static volatile SingularAttribute<Treinamento, String> tipo;
	public static volatile ListAttribute<Treinamento, Documento> documentoList;
	public static volatile SingularAttribute<Treinamento, Date> proxTreinamento;
	public static volatile SingularAttribute<Treinamento, String> localidade;
	public static volatile ListAttribute<Treinamento, Colaborador> colaboradorList;
	public static volatile SingularAttribute<Treinamento, String> prorrogacao;
	public static volatile SingularAttribute<Treinamento, TipoTreinamento> tipoTreinamentoId;
	public static volatile SingularAttribute<Treinamento, Date> dataFinalizacao;
	public static volatile SingularAttribute<Treinamento, Treinador> treinadorId;
	public static volatile SingularAttribute<Treinamento, String> horarioFinal;
	public static volatile SingularAttribute<Treinamento, Date> dataTreinamento;
	public static volatile SingularAttribute<Treinamento, Itens> itensId;
	public static volatile SingularAttribute<Treinamento, Long> id;
	public static volatile SingularAttribute<Treinamento, String> status;

}

