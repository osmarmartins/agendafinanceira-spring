package agendafinanceira.repositories.helpers;

import java.util.List;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.filters.UsuarioFilter;

public interface UsuarioRepositoryQueries {
	
	public List<UsuarioModel> filtrar(UsuarioFilter filtro);

}
