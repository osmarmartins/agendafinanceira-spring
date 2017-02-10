package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.services.exception.ExcluirEntidadeException;
import agendafinanceira.services.exception.SenhasNaoConferemException;
import agendafinanceira.services.exception.UsuarioJaCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioModel salvar(UsuarioModel usuario){
		
		System.out.println(usuario.toString());

		Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByLoginIgnoreCase(usuario.getLogin());
		
		System.out.println(usuarioOptional.toString());

		if (usuarioOptional.isPresent() && !usuarioOptional.get().getIdUsuario().equals(usuario.getIdUsuario()) ) {
			throw new UsuarioJaCadastradoException("Login já cadastrado!");
		}
		
		if (!usuario.getSenha().equals(usuario.getConfirmaSenha()) ){
			throw new SenhasNaoConferemException("Senhas informadas não conferem!");
		}
		
		return usuarioRepository.saveAndFlush(usuario);
	}

	@Transactional
	public void excluir(UsuarioModel usuario){

		try {
			usuarioRepository.delete(usuario);
			usuarioRepository.flush();
		} catch (ExcluirEntidadeException e) {
			throw new ExcluirEntidadeException("Não foi possível excluir o usuário! Verifique a existência de registros dependentes deste usuário");
		}		
	}

	@Transactional
	public void alterarStatus(UsuarioModel usuario) {
		
		System.out.println(usuario.toString());
		
		usuario.setAtivo(usuario.getAtivo().equals(Ativo.ATIVO)?Ativo.INATIVO:Ativo.ATIVO);
		usuarioRepository.save(usuario);		
		
		System.out.println(usuario.toString());
	}

}

