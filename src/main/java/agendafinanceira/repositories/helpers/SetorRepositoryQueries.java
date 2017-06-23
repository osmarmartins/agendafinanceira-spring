package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.filters.SetorFilter;

public interface SetorRepositoryQueries {
	
	public Page<SetorModel> filtrar(SetorFilter filtro, Pageable pageable);

}
