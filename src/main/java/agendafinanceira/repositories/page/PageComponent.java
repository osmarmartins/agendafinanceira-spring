package agendafinanceira.repositories.page;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageComponent {
	
	public void initializer(Pageable page, Criteria criteria){
		
		int firstResult = page.getPageNumber() * page.getPageSize();
		int maxResults = page.getPageSize();
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		
		
		Sort sort = page.getSort();
		if (sort != null){
			Sort.Order order = sort.iterator().next();
			String campoOrdenacao = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(campoOrdenacao) : Order.desc(campoOrdenacao));
		}
		
	}


}
