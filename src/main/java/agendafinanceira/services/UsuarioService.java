package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.security.Encrpty;
import agendafinanceira.services.exception.ExcluirEntidadeException;
import agendafinanceira.services.exception.HashMD5Exception;
import agendafinanceira.services.exception.SenhasNaoConferemException;
import agendafinanceira.services.exception.UsuarioEmailException;
import agendafinanceira.services.exception.UsuarioLoginException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioModel salvar(UsuarioModel usuario){
		
		Optional<UsuarioModel> usuarioOptional; 
				
		usuarioOptional = usuarioRepository.findByLoginIgnoreCase(usuario.getLogin());
		
		if (usuarioOptional.isPresent() && usuarioOptional.get().getLogin().equals(usuario.getLogin()) ) {
			throw new UsuarioLoginException("Login já cadastrado!");
		}
		

		usuarioOptional = usuarioRepository.findByEmailIgnoreCase(usuario.getEmail());
		
		if (usuarioOptional.isPresent() && usuarioOptional.get().getEmail().equals(usuario.getEmail()) ) {
			throw new UsuarioEmailException("e-mail já cadastrado!");
		}
		
		if (usuario.getSenha().isEmpty()){
			// preserva a senha original
			UsuarioModel usuarioAtual = usuarioRepository.findOne(usuario.getIdUsuario());
			usuario.setSenha(usuarioAtual.getSenha());
		}else{
			if (!usuario.getSenha().equals(usuario.getConfirmaSenha()) ){
				throw new SenhasNaoConferemException("Senhas informadas não conferem!");
			}

			try {
				usuario.setSenha(Encrpty.getMD5(usuario.getSenha()));
			} catch (HashMD5Exception e) {
				throw new HashMD5Exception("Erro na codificação da senha!" + e.getMessage());
			}
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

