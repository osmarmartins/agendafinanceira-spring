package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.helpers.SetorQueries;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Long> { //, SetorQueries{
	
}

