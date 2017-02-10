package agendafinanceira.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.ContaModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.ContaRepository;
import agendafinanceira.services.exception.DescricaoExistenteException;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	public ContaModel salvar(ContaModel conta) {

		Optional<ContaModel> contaOptional = contaRepository.findByDescricaoIgnoreCase(conta.getDescricao());
		if (contaOptional.isPresent() && !contaOptional.get().getIdConta().equals(conta.getIdConta()) ) {
			throw new DescricaoExistenteException("Setor já cadastrado");
		}

		return contaRepository.saveAndFlush(conta);
	}

	@Transactional
	public void excluir(ContaModel conta) {
		try {
			contaRepository.delete(conta);
			contaRepository.flush();
		} catch (ExcluirEntidadeException e) {
			throw new ExcluirEntidadeException("Não foi possível excluir a conta! Verifique se está em uso nos lançamentos financeiros.");
		}		
	}
	
	@Transactional
	public void alterarStatus(ContaModel conta){
		conta.setAtivo(conta.getAtivo().equals(Ativo.ATIVO)?Ativo.INATIVO:Ativo.ATIVO);
		contaRepository.save(conta);
	}
}
