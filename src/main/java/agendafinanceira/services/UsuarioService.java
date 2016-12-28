package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.services.exception.SenhasNaoConferemException;
import agendafinanceira.services.exception.UsuarioJaCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioModel salvar(UsuarioModel usuario){
		
		Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByLoginIgnoreCase(usuario.getLogin());
		
		if (usuarioOptional.isPresent()){
			throw new UsuarioJaCadastradoException("Login já cadastrado!");
		}
		
		if (!usuario.getSenha().equals(usuario.getConfirmaSenha()) ){
			throw new SenhasNaoConferemException("Senhas informadas não conferem!");
		}
		
		return usuarioRepository.saveAndFlush(usuario);
	}

	@Transactional
	public void excluir(UsuarioModel usuario){
		usuarioRepository.delete(usuario);
	}

}

