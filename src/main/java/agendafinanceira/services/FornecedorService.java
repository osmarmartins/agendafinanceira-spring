package agendafinanceira.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.repositories.FornecedorRepository;
import agendafinanceira.repositories.filters.FornecedorFilter;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public List<FornecedorModel> listarFornecedores(){
		return fornecedorRepository.findAll();
	}

	public Page<FornecedorModel> filtrar(FornecedorFilter fornecedorFilter, Pageable pageable) {
		return fornecedorRepository.filtrar(fornecedorFilter, pageable);
	}

}
