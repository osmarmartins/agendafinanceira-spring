package agendafinanceira.repositories.helpers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.filters.UsuarioFilter;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public Page<UsuarioModel> filtrar(UsuarioFilter filtro, Pageable page) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);
		
		int firstResult = page.getPageNumber() * page.getPageSize();
		int maxResults = page.getPageSize();
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), page, total(filtro));
	}

	private Long total(UsuarioFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);
		criteria.setProjection(Projections.rowCount());
		
		adicionarFiltro(filtro, criteria);

		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {
		if (filtro.getNome() != null){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
	}

	
	

/*
	@SuppressWarnings("unchecked")
	@Override
	public Page<UsuarioModel> filtrar(UsuarioFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(UsuarioFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

		}
		
	}
*/

}
