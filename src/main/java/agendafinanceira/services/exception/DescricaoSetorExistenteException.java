package agendafinanceira.services.exception;

public class DescricaoSetorExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DescricaoSetorExistenteException(String message){
		super(message);
	}

}
