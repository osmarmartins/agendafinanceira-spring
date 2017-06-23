package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.SetorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.SetorRepository;
import agendafinanceira.services.exception.DescricaoExistenteException;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

	@Transactional
	public SetorModel salvar(SetorModel setor) {

		Optional<SetorModel> setorOptional = setorRepository.findByDescricaoIgnoreCase(setor.getDescricao());
		if (setorOptional.isPresent() && !setorOptional.get().getIdSetor().equals(setor.getIdSetor()) ) {
			throw new DescricaoExistenteException("Setor já cadastrado");
		}

		return setorRepository.saveAndFlush(setor);
	}

	@Transactional
	public void excluir(SetorModel setor) {
		try {
			setorRepository.delete(setor);
			setorRepository.flush();
		} catch (ExcluirEntidadeException e) {
			throw new ExcluirEntidadeException("Não foi possível excluir o setor! Verifique se está em uso nos lançamentos financeiros.");
		}		
	}
	
	@Transactional
	public void alterarStatus(SetorModel setor){
		setor.setAtivo(setor.getAtivo().equals(Ativo.ATIVO)?Ativo.INATIVO:Ativo.ATIVO);
		setorRepository.save(setor);
	}

}
