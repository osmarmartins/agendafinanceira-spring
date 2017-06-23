package agendafinanceira.repositories.helpers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import agendafinanceira.models.SetorModel;
import agendafinanceira.repositories.filters.SetorFilter;
import agendafinanceira.repositories.page.PageComponent;

public class SetorRepositoryImpl implements SetorRepositoryQueries {
	

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PageComponent PageComponent;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public Page<SetorModel> filtrar(SetorFilter filtro, Pageable page) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(SetorModel.class);

		PageComponent.initializer(page, criteria);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), page, total(filtro));
	}

	private Long total(SetorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(SetorModel.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(SetorFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}

		}
		
	}

}
