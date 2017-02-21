package agendafinanceira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agendafinanceira.models.ContatoModel;
import agendafinanceira.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public ContatoModel buscarPorId(Long id){
		return contatoRepository.buscarPorId(id); 
	}
	

}
