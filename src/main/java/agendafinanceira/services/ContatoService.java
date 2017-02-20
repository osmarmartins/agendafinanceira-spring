package agendafinanceira.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agendafinanceira.models.ContatoModel;
import agendafinanceira.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	public ContatoRepository contatoRepository;
	
	public List<ContatoModel> listaContatos(Long fornecedor){
		return contatoRepository.findByFornecedor(fornecedor);
	}

}
