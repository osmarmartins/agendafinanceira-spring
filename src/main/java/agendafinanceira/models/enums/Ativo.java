package agendafinanceira.models.enums;

public enum Ativo {
	
	INATIVO("Inativo", false), 
	ATIVO("Ativo", true);
	
	private String descricao;
	private Boolean valor;
	
	private Ativo(String descricao, Boolean valor){
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao(){
		return descricao;
	}
	
	public Boolean getValor(){
		return valor;
	}
}
