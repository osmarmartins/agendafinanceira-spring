package agendafinanceira.repositories.filters;

import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoUsuario;

public class UsuarioFilter {
	
	private Long idUsuario;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private TipoUsuario administrador;
	private Ativo ativo;

	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(TipoUsuario administrador) {
		this.administrador = administrador;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}	
	
	
		
}
