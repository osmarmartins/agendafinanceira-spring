package agendafinanceira.controllers.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import agendafinanceira.services.exception.DescricaoExistenteException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	

	@ExceptionHandler(DescricaoExistenteException.class)
	public ResponseEntity<String> handleDescricaoSetorExistenteException(DescricaoExistenteException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
