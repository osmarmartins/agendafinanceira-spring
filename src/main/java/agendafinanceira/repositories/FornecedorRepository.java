package agendafinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.repositories.helpers.FornecedorRepositoryQueries;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long>, FornecedorRepositoryQueries {

}
