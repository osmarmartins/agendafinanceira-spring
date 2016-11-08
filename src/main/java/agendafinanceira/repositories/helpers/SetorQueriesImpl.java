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
import org.springframework.util.StringUtils;

import agendafinanceira.models.SetorModel;
import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.filters.SetorFilter;
import agendafinanceira.utils.PaginacaoUtil;

public class SetorQueriesImpl implements SetorQueries {
	

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	public Page<SetorModel> filtrar(SetorFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(SetorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(SetorFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("nome", filtro.getDescricao(), MatchMode.ANYWHERE));
			}

		}
		
	}

	

}
