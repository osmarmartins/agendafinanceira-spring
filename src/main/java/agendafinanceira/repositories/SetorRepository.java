package agendafinanceira.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.helpers.SetorRepositoryQueries;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Long>, SetorRepositoryQueries{
	
	public Optional<SetorModel> findByDescricaoIgnoreCase(String descricao);
	
}

