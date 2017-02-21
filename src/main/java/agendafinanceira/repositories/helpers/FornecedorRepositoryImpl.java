package agendafinanceira.repositories.helpers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.repositories.filters.FornecedorFilter;
import agendafinanceira.repositories.page.PageComponent;

public class FornecedorRepositoryImpl implements FornecedorRepositoryQueries {


	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PageComponent PageComponent;
	

	@Transactional(readOnly = true)
	@Override
	public FornecedorModel buscarFornecedor(Long id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(FornecedorModel.class);
		criteria.createAlias("contatos", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("idFornecedor", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (FornecedorModel) criteria.uniqueResult();
	}	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public Page<FornecedorModel> filtrar(FornecedorFilter filtro, Pageable page) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(FornecedorModel.class);
		
		PageComponent.initializer(page, criteria);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), page, total(filtro));
	}
	
	private Long total(FornecedorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(FornecedorModel.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(FornecedorFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getNomeFantasia())) {
				criteria.add(Restrictions.ilike("nomeFantasia", filtro.getNomeFantasia(), MatchMode.ANYWHERE));
			}

		}
		
	}

}
