package agendafinanceira.repositories.filters;

import agendafinanceira.models.enums.Ativo;

public class SetorFilter {
	
	private Long idSetor;
	private String descricao;
	private Ativo ativo;

	public Long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
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
