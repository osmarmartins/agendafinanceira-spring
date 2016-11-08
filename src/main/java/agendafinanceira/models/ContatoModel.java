package agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import agendafinanceira.models.enums.Ativo;

@Entity
@Table(name="contatos")
public class ContatoModel implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contato")
	private Long idContato;
	
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private FornecedorModel fornecedor;
	
	private String contato;
	
	private String endereco;
	
	private String complemento;
	
	private String bairro;
	
	private String cep;
	
	private String cidade;
	
	private String uf;
	
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	@Email(message="Informe um e-mail válido!")
	private String email;
	
	private String site;
	
	@Enumerated
	@NotBlank(message = "Informe se o fornecedor está ativo ou não.")	
	private Ativo ativo;

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}

	public FornecedorModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorModel fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContato == null) ? 0 : idContato.hashCode());
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
		ContatoModel other = (ContatoModel) obj;
		if (idContato == null) {
			if (other.idContato != null)
				return false;
		} else if (!idContato.equals(other.idContato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContatoModel [idContato=" + idContato + ", fornecedor=" + fornecedor + ", contato=" + contato
				+ ", endereco=" + endereco + ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep
				+ ", cidade=" + cidade + ", uf=" + uf + ", telefone1=" + telefone1 + ", telefone2=" + telefone2
				+ ", telefone3=" + telefone3 + ", email=" + email + ", site=" + site + ", ativo=" + ativo + "]";
	}
	

}
