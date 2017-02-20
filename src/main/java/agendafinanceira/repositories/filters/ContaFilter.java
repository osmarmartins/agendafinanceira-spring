package agendafinanceira.repositories.filters;

import agendafinanceira.models.enums.Ativo;

public class ContaFilter {
	
	private Long idConta;
	private String descricao;
	private Ativo ativo;

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	


	
}
