package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.ContatoModel;
import agendafinanceira.repositories.helpers.ContatoRepositoryQueries;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long>, ContatoRepositoryQueries{

}
