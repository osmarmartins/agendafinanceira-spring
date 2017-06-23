package agendafinanceira.services.exception;

public class DescricaoExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DescricaoExistenteException(String message){
		super(message);
	}

}
