package agendafinanceira.controllers.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class Paginacao<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	public Paginacao(Page<T> page, HttpServletRequest httpServletRequest){
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
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

}
