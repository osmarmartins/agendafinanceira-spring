package agendafinanceira.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import agendafinanceira.models.enums.SituacaoPagamento;

@Entity
@Table(name="pgtos")
public class PagamentoModel implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgto")
	private Long idPgto;
	
	@ManyToOne
	@JoinColumn(name="id_setor")
	private SetorModel setor;
	
	@ManyToOne
	@JoinColumn(name="id_conta")
	private ContaModel conta;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private FornecedorModel fornecedor;
	
	private String documento;
	
	@Temporal(TemporalType.DATE)
	private Date emissao;
	
	private String historico;
	
	@Enumerated
	private SituacaoPagamento situacao;
	
	@OneToMany(mappedBy="pagamento")
	private List<PagamentoParcelaModel> parcelas;

	public Long getIdPgto() {
		return idPgto;
	}

	public void setIdPgto(Long idPgto) {
		this.idPgto = idPgto;
	}

	public SetorModel getSetor() {
		return setor;
	}

	public void setSetor(SetorModel setor) {
		this.setor = setor;
	}

	public ContaModel getConta() {
		return conta;
	}

	public void setConta(ContaModel conta) {
		this.conta = conta;
	}

	public FornecedorModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorModel fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public SituacaoPagamento getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPagamento situacao) {
		this.situacao = situacao;
	}

	public List<PagamentoParcelaModel> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<PagamentoParcelaModel> parcelas) {
		this.parcelas = parcelas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPgto == null) ? 0 : idPgto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoModel other = (PagamentoModel) obj;
		if (idPgto == null) {
			if (other.idPgto != null)
				return false;
		} else if (!idPgto.equals(other.idPgto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagamentoModel [idPgto=" + idPgto + ", setor=" + setor + ", conta=" + conta + ", fornecedor="
				+ fornecedor + ", documento=" + documento + ", emissao=" + emissao + ", historico=" + historico
				+ ", situacao=" + situacao + ", parcelas=" + parcelas + "]";
	}
	

}
