package agendafinanceira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.PagamentoModel;
import agendafinanceira.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Transactional
	public void salvar(PagamentoModel pagamentoModel){
		pagamentoRepository.save(pagamentoModel);
	}
	
	@Transactional
	public void excluir(PagamentoModel pagamentoModel){
		pagamentoRepository.delete(pagamentoModel);
	}
	
}
