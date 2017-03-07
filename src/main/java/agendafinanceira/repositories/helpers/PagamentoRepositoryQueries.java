package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.PagamentoModel;
import agendafinanceira.repositories.filters.PagamentoFilter;

public interface PagamentoRepositoryQueries {
	
	public Page<PagamentoModel> filtrar(PagamentoFilter filtro, Pageable pageable);

}
