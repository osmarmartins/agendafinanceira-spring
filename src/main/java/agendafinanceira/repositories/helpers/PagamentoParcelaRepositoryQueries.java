package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.PagamentoParcelaModel;
import agendafinanceira.repositories.filters.PagamentoFilter;

public interface PagamentoParcelaRepositoryQueries {

	public Page<PagamentoParcelaModel> filtrar(PagamentoFilter filtro, Pageable pageable);

}
