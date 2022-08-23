package bac.com.br.hibernate.entidade;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "funcao")
public class Funcao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cargo")
    private Long id;
    @Column(name = "funcao")
    private String funcao;

    private String condicao;

    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Setor idSetor;


    @JoinColumn(name = "lotacaoId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotacao lotacaoId;

    @JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Domicilio domicilioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public Setor getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Setor idSetor) {
        this.idSetor = idSetor;
    }

    public Lotacao getLotacaoId() {
        return lotacaoId;
    }

    public void setLotacaoId(Lotacao lotacaoId) {
        this.lotacaoId = lotacaoId;
    }

    public Domicilio getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(Domicilio domicilioId) {
        this.domicilioId = domicilioId;
    }
}
