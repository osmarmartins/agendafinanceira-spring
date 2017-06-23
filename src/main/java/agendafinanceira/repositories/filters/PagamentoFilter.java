package agendafinanceira.repositories.filters;

import java.util.Date;

import agendafinanceira.models.enums.SituacaoPagamento;

public class PagamentoFilter {

	private String nome;
	private Date vctoInicial;
	private Date vctoFinal;
	private String historico;
	private SituacaoPagamento situacaoPgto;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getVctoInicial() {
		return vctoInicial;
	}
	public void setVctoInicial(Date vctoInicial) {
		this.vctoInicial = vctoInicial;
	}
	public Date getVctoFinal() {
		return vctoFinal;
	}
	public void setVctoFinal(Date vctoFinal) {
		this.vctoFinal = vctoFinal;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public SituacaoPagamento getSituacaoPgto() {
		return situacaoPgto;
	}
	public void setSituacaoPgto(SituacaoPagamento situacaoPgto) {
		this.situacaoPgto = situacaoPgto;
	}
	

	
	

}
