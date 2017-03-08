package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import agendafinanceira.models.PagamentoParcelaModel;
import agendafinanceira.repositories.helpers.PagamentoParcelaRepositoryQueries;

public interface PagamentoParcelaRepository extends JpaRepository<PagamentoParcelaModel, Long>, PagamentoParcelaRepositoryQueries {

}
