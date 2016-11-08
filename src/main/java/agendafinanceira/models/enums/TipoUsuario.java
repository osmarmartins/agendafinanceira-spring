package agendafinanceira.models.enums;

public enum TipoUsuario {
	
	USUARIO(0),
	ADMINISTRADOR(1);
	
	private int tipoUsuario;
	
	private TipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}
		
}
