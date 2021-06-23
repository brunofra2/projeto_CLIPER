package bac.com.br.hibernate.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Descricao.class)
public abstract class Descricao_ {

	public static volatile SingularAttribute<Descricao, String> area;
	public static volatile SingularAttribute<Descricao, String> escolaridadeDes;
	public static volatile SingularAttribute<Descricao, String> escolaridadeMin;
	public static volatile ListAttribute<Descricao, Documento> documentoList;
	public static volatile SingularAttribute<Descricao, String> supervisao;
	public static volatile ListAttribute<Descricao, Historico> historicoList;
	public static volatile SingularAttribute<Descricao, String> treinamentoLegais;
	public static volatile SingularAttribute<Descricao, String> habilitacaoProfissional;
	public static volatile SingularAttribute<Descricao, String> integracao;
	public static volatile SingularAttribute<Descricao, String> lidera;
	public static volatile SingularAttribute<Descricao, String> faixaEtaria;
	public static volatile SingularAttribute<Descricao, Funcao> idFuncaoId;
	public static volatile SingularAttribute<Descricao, String> experiencia;
	public static volatile SingularAttribute<Descricao, String> missao;
	public static volatile SingularAttribute<Descricao, Setor> idSetorId;
	public static volatile SingularAttribute<Descricao, Long> id;
	public static volatile SingularAttribute<Descricao, String> habilidade;
	public static volatile SingularAttribute<Descricao, String> sexo;
	public static volatile SingularAttribute<Descricao, String> responsabilidades;
	public static volatile SingularAttribute<Descricao, String> descricaoDetalhada;

}

