package agendafinanceira.repositories.filters;

import java.util.Date;

import agendafinanceira.models.ContaModel;
import agendafinanceira.models.FornecedorModel;
import agendafinanceira.models.SetorModel;
import agendafinanceira.models.enums.SituacaoPagamento;

public class PagamentoFilter {
	
	private Long idPagamento;
	private SetorModel setor;
	private ContaModel conta;
	private FornecedorModel fornecedor;
	private String documento;
	private Date emissao;
	private String historico;
	private SituacaoPagamento situacao;

	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
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
	

}
