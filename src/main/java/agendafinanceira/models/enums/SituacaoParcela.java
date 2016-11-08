package agendafinanceira.models.enums;

public enum SituacaoParcela {

	NOVO(0), AGENDADO(1), PROGRAMADO(2), 
	LIQUIDADO(3), BAIXADO(4), CANCELADO(9);

	private int situacaoParcela;

	private SituacaoParcela(int situacaoParcela) {
		this.situacaoParcela = situacaoParcela;
	}

	public int getSituacaoParcela() {
		return situacaoParcela;
	}

}
