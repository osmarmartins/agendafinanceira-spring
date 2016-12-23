package agendafinanceira.controllers.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import agendafinanceira.services.exception.DescricaoSetorExistenteException;
import agendafinanceira.services.exception.UsuarioJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UsuarioJaCadastradoException.class)
	public ResponseEntity<String> handleUsuarioJaCadastradoException(UsuarioJaCadastradoException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	
	@ExceptionHandler(DescricaoSetorExistenteException.class)
	public ResponseEntity<String> handleDescricaoSetorExistenteException(DescricaoSetorExistenteException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
