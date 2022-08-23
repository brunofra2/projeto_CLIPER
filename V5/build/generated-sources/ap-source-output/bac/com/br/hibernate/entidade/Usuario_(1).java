package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile ListAttribute<Usuario, Treinador> treinadorList;
	public static volatile SingularAttribute<Usuario, String> codigo;
	public static volatile SingularAttribute<Usuario, String> tipo;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile ListAttribute<Usuario, Backup> backupList;

}

