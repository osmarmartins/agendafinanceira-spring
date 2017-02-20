package agendafinanceira.repositories.filters;

import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoPessoa;

public class FornecedorFilter {

	private Long idFornecedor;
	private String nomeFantasia;
	private String razaoSocial;
	private TipoPessoa tipo;
	private String documento;
	private Ativo ativo;
	
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Ativo getAtivo() {
		return ativo;
	}
	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	
	
}
