package agendafinanceira.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.FornecedorRepository;
import agendafinanceira.repositories.filters.FornecedorFilter;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public List<FornecedorModel> listarFornecedores(){
		return fornecedorRepository.findAll();
	}
	
	@Transactional
	public FornecedorModel salvar(FornecedorModel fornecedor){
		return fornecedorRepository.saveAndFlush(fornecedor);
	}

	@Transactional
	public void alterarStatus(FornecedorModel fornecedor) {
		fornecedor.setAtivo(fornecedor.getAtivo().equals(Ativo.ATIVO)?Ativo.INATIVO:Ativo.ATIVO);
		fornecedorRepository.save(fornecedor);
	}
	
	@Transactional
	public void excluir(FornecedorModel fornecedor){
		try {
			fornecedorRepository.delete(fornecedor);
			fornecedorRepository.flush();
		} catch (ExcluirEntidadeException e) {
			throw new ExcluirEntidadeException("Não foi possível excluir o fornecedor! Verifique se está em uso nos lançamentos financeiros.");
		}		
	}
	
	public Page<FornecedorModel> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable) {
		return fornecedorRepository.filtrar(fornecedorFilter, pageable);
	}

	public FornecedorModel buscarFornecedor(Long id) {
		return fornecedorRepository.buscarFornecedor(id);
	}


}
