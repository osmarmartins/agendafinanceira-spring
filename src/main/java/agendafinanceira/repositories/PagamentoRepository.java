package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agendafinanceira.models.PagamentoModel;
import agendafinanceira.repositories.helpers.PagamentoRepositoryQueries;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long>, PagamentoRepositoryQueries  {


}
