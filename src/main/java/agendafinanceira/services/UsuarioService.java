package agendafinanceira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public void salvar(UsuarioModel usuario){
		usuarioRepository.save(usuario);
	}

	@Transactional
	public void excluir(UsuarioModel usuario){
		usuarioRepository.delete(usuario);
	}

}

