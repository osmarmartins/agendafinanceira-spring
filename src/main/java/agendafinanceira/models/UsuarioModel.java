package agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Size(max=100, message="Não é permitido mais de 100 caracteres para o nome")
	private String nome;

	@Size(max=150, message="Não é permitido mais de 150 caracteres para o e-mail")
	private String email;

	@Size(max=50, message="Não é permitido mais de 50 caracteres para o login")
	private String login;

	private String senha;

	@Enumerated
	@NotBlank(message = "Informe o tipo de usuário")
	private TipoUsuario administrador;

	@Enumerated
	@NotBlank(message = "Informe se o usuário está ativo ou não")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		UsuarioModel other = (UsuarioModel) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", nome=" + nome + ", email=" + email + ", login=" + login
				+ ", senha=" + senha + ", administrador=" + administrador + ", ativo=" + ativo + "]";
	}

}
