package agendafinanceira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.ContatoModel;
import agendafinanceira.models.FornecedorModel;
import agendafinanceira.repositories.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public ContatoModel buscarPorId(Long id) {
		return contatoRepository.buscarPorId(id);
	}

	@Transactional
	public ContatoModel salvar(FornecedorModel fornecedor, ContatoModel contato) {
		
		contato.setFornecedor(fornecedor);
		return contatoRepository.saveAndFlush(contato);

	}

}
