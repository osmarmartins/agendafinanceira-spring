package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.controllers.page.Paginacao;
import agendafinanceira.models.PagamentoModel;
import agendafinanceira.models.PagamentoParcelaModel;
import agendafinanceira.repositories.PagamentoParcelaRepository;
import agendafinanceira.repositories.filters.PagamentoFilter;
import agendafinanceira.services.PagamentoService;


@Controller
@RequestMapping("/pagamento/baixa")
public class BaixaController {
	
	@Autowired
	private PagamentoParcelaRepository pagamentoParcelaRepository;
	
	@Autowired
	private PagamentoService pagamentoService;
	

	@GetMapping("/parcela/{pagamento}")
	public ModelAndView baixarParcela(PagamentoParcelaModel parcela){
		ModelAndView mv = new ModelAndView("pagamentoBaixa/BaixarParcela");
		return mv;
	}
	
	
	@PostMapping("/parcela")
	public ModelAndView cadastrar(@Valid PagamentoParcelaModel pagamentoParcelaModel, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return baixarParcela(pagamentoParcelaModel);
		}
		
//		pagamentoService.salvar(pagamentoParcelaModel);
		attributes.addFlashAttribute("mensagem", "Pagamento salvo com sucesso!");
		return new ModelAndView("redirect:/pagamento/baixa");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PagamentoFilter pagamentoFilter, BindingResult result
			, @PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("pagamentoBaixa/ListarBaixa");
		Paginacao<PagamentoParcelaModel> paginacao = new Paginacao<>(pagamentoParcelaRepository.filtrar(pagamentoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}	
	

}
