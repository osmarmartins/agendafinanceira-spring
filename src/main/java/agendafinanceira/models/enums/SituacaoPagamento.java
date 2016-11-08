package agendafinanceira.models.enums;

public enum SituacaoPagamento {
	
	NOVO(0), EMABERTO(1), FINALIZADO(2);
	
	private int situacaoPagamento;
	
	private SituacaoPagamento(int situacaoPagamento){
		this.situacaoPagamento = situacaoPagamento;
	}
	
	public int getSitucaoPagamento(){
		return situacaoPagamento;
	}

}
