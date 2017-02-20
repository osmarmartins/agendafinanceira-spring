package agendafinanceira.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agendafinanceira.models.ContatoModel;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long>{

	public List<ContatoModel> findByFornecedor(Long fornecedor);
	

}
