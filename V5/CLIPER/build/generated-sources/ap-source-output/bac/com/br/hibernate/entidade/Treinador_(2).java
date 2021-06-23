package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Treinador.class)
public abstract class Treinador_ {

	public static volatile SingularAttribute<Treinador, String> emailSupervisor;
	public static volatile SingularAttribute<Treinador, Empresa> empresaId;
	public static volatile SingularAttribute<Treinador, String> formacao;
	public static volatile SingularAttribute<Treinador, String> nome;
	public static volatile ListAttribute<Treinador, Treinamento> treinamentoList;
	public static volatile SingularAttribute<Treinador, Long> id;
	public static volatile SingularAttribute<Treinador, Usuario> usuarioId;
	public static volatile SingularAttribute<Treinador, String> email;
	public static volatile SingularAttribute<Treinador, String> supervisor;
	public static volatile SingularAttribute<Treinador, Setor> idSetor;

}

