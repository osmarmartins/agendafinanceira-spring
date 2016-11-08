package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.helpers.UsuarioQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{ //, UsuarioQueries{
	
}
