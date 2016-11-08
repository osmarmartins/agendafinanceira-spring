package agendafinanceira.models.enums;

public enum TipoPessoa {
	
	PF(0),
	PJ(1);
	
	private int tipoPessoa;
	
	private TipoPessoa(int tipoPessoa){
		this.tipoPessoa = tipoPessoa;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}
	
}
