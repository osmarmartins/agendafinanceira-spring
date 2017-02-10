package agendafinanceira.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.ContaModel;
import agendafinanceira.repositories.helpers.ContaRepositoryQueries;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, Long>, ContaRepositoryQueries   {
	
	public Optional<ContaModel> findByDescricaoIgnoreCase(String descricao);

}
