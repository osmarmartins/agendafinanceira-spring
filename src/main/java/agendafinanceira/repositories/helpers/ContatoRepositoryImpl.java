package agendafinanceira.repositories.helpers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.ContatoModel;

public class ContatoRepositoryImpl implements ContatoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional(readOnly = true)
	@Override
	public ContatoModel buscarPorId(Long id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ContatoModel.class);
		criteria.createAlias("fornecedor", "f", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("idContato", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (ContatoModel) criteria.uniqueResult();
	}

}
