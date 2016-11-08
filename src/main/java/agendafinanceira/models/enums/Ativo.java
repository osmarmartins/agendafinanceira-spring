package agendafinanceira.models.enums;

public enum Ativo {
	
	INATIVO(0), 
	ATIVO(1);
	
	private int tipoAtivo;
	
	private Ativo(int tipoAtivo){
		this.tipoAtivo = tipoAtivo;
	}

	public int getTipoAtivo() {
		return tipoAtivo;
	}

}
