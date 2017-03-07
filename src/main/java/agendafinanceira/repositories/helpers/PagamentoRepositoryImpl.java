package agendafinanceira.repositories.helpers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

import agendafinanceira.models.PagamentoModel;
import agendafinanceira.models.UsuarioModel;
import agendafinanceira.repositories.filters.PagamentoFilter;
import agendafinanceira.repositories.page.PageComponent;

public class PagamentoRepositoryImpl implements PagamentoRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PageComponent pageComponent;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public Page<PagamentoModel> filtrar(PagamentoFilter filtro, Pageable page) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(PagamentoModel.class);
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<PagamentoModel> criteriaQuery = criteriaBuilder.createQuery(PagamentoModel.class);
		
		Root<PagamentoModel> pagamentoModel = criteriaQuery.from(PagamentoModel.class);
		pagamentoModel.fetch("pgtos_parcela");
		criteriaQuery.select(pagamentoModel);
		
		TypedQuery<PagamentoModel> query = manager.createQuery(criteriaQuery);
		Criteria criteria = (Criteria) query.getResultList();
		
		pageComponent.initializer(page, criteria);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), page, total(filtro));
	}

	private Long total(PagamentoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(UsuarioModel.class);
		criteria.setProjection(Projections.rowCount());
		
		adicionarFiltro(filtro, criteria);

		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(PagamentoFilter filtro, Criteria criteria) {
		
		if (filtro.getNome() != null){
			criteria.add(Restrictions.ilike("nome_fantasia", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		// TODO filtro por vencimento (inicial e final)
		
		// TODO filtro por histórico
		
		// TODO filtro por situação
	}
}
