package agendafinanceira.services.exception;

public class SenhasNaoConferemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SenhasNaoConferemException(String message){
		super(message);
	}

}
