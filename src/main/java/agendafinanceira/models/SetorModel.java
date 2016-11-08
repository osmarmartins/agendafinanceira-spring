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

@Entity
@Table(name="setores")
public class SetorModel implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_setor")
	private Long idSetor;
	
	@NotBlank
	@Size(max=100, message="Descrição não pode ultrapassar 100 caracteres.")
	private String descricao;
	
	@Enumerated
	@NotBlank(message = "Informe se o setor está ativo ou não.")	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSetor == null) ? 0 : idSetor.hashCode());
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
		SetorModel other = (SetorModel) obj;
		if (idSetor == null) {
			if (other.idSetor != null)
				return false;
		} else if (!idSetor.equals(other.idSetor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SetorModel [idSetor=" + idSetor + ", descricao=" + descricao + ", ativo=" + ativo + "]";
	}
	
}
