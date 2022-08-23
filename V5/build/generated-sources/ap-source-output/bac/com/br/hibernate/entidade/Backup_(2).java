package bac.com.br.hibernate.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Backup.class)
public abstract class Backup_ {

	public static volatile SingularAttribute<Backup, Date> dataBackup;
	public static volatile SingularAttribute<Backup, Long> id;
	public static volatile SingularAttribute<Backup, String> caminho;
	public static volatile SingularAttribute<Backup, Usuario> usuarioId;

}

