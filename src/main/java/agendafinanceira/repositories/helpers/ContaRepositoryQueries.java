package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.ContaModel;
import agendafinanceira.repositories.filters.ContaFilter;

public interface ContaRepositoryQueries {
	
	public Page<ContaModel> filtrar(ContaFilter filtro, Pageable pageable);

}
