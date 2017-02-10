package agendafinanceira.controllers.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import agendafinanceira.services.exception.DescricaoExistenteException;
import agendafinanceira.services.exception.UsuarioJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UsuarioJaCadastradoException.class)
	public ResponseEntity<String> handleUsuarioJaCadastradoException(UsuarioJaCadastradoException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	
	@ExceptionHandler(DescricaoExistenteException.class)
	public ResponseEntity<String> handleDescricaoSetorExistenteException(DescricaoExistenteException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
