package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.repositories.filters.FornecedorFilter;

public interface FornecedorRepositoryQueries {
	
	public Page<FornecedorModel> filtrar(FornecedorFilter filtro, Pageable pageable);

}
