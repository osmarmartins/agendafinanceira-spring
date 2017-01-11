package agendafinanceira.models.enums;

public enum Ativo {
	
	INATIVO("Inativo"), 
	ATIVO("Ativo");
	
	private String descricao;
	
	private Ativo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
		
}
