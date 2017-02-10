package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agendafinanceira.models.PagamentoModel;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long>{ //, PagementoQueries  {


}
