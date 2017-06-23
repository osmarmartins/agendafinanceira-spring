package agendafinanceira.controllers.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponentsBuilder;

public class Paginacao<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	private String orderField;
	private String orderDirection;
	
	public Paginacao(Page<T> page, HttpServletRequest httpServletRequest){
		this.page = page;
		
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);		
		
		StringBuilder uri = new StringBuilder(uriBuilder.toUriString());
		if (uri.indexOf("sort")>0){
			Sort.Order order = page.getSort().iterator().next();
			this.orderField = order.getProperty();
			this.orderDirection = order.isAscending() ? "asc" : "desc"; 
		}
	}
	
	public List<T> getConteudo(){
		return page.getContent();
	}
	
	public int getAtual(){
		return page.getNumber();
	}
	
	public boolean isVazia(){
		return page.getContent().isEmpty();
	}
	
	public boolean isPrimeira(){
		return page.isFirst();
	}
	
	public boolean isUltima(){
		return page.isLast();
	}
	
	public int getTotal(){
		return page.getTotalPages();
	}
	
	public String urlPagina(int pagina){
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public boolean isOrder(String campo){
		return orderField!=null ? orderField.equalsIgnoreCase(campo) : false;
	}
	
	public boolean isDescendent(String campo){
		return orderDirection!=null ? orderDirection.equals("desc") : false;
	}
	
	public String orderChange(String campo){

		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String direction = "asc";
		if (orderDirection!=null){
			direction = orderDirection.equals("desc") ? "asc" : "desc";
		}
		
		String valorSort = String.format("%s,%s", campo, direction);
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();		
	}
	
}	
	
