package agendafinanceira.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.helpers.UsuarioRepositoryQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>, UsuarioRepositoryQueries {
	
	Optional<UsuarioModel> findByLoginIgnoreCase(String login); 
	
}
