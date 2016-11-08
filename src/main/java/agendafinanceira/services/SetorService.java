package agendafinanceira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Transactional
	public void salvar(SetorModel setor){
		setorRepository.save(setor);
	}

	@Transactional
	public void excluir(SetorModel setor){
		setorRepository.delete(setor);
	}
	
}
