package agendafinanceira.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.SetorModel;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Long> { //, SetorQueries{
	
	public Optional<SetorModel> findByDescricaoIgnoreCase(String descricao);
	
}

