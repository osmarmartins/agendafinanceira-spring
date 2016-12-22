package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.SetorRepository;
import agendafinanceira.services.exception.DescricaoSetorExistenteException;

@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

	@Transactional
	public SetorModel salvar(SetorModel setor) {

		Optional<SetorModel> setorOptional = setorRepository.findByDescricaoIgnoreCase(setor.getDescricao());
		if (setorOptional.isPresent()) {
			throw new DescricaoSetorExistenteException("Setor já cadastrado");
		}

		return setorRepository.saveAndFlush(setor);
	}

	@Transactional
	public void excluir(SetorModel setor) {
		setorRepository.delete(setor);
	}

}
