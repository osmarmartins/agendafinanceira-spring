package agendafinanceira.repositories.helpers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.filters.UsuarioFilter;

public interface UsuarioQueries {
	
	public Page<UsuarioModel> filtrar(UsuarioFilter filtro, Pageable pageable);

}
