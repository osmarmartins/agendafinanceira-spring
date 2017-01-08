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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.models.PagamentoModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.PagamentoRepository;
import agendafinanceira.repositories.filters.PagamentoFilter;
import agendafinanceira.services.PagamentoService;

@Controller
public class PagamentoController {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	
	@RequestMapping("pagamento/novo")
	public ModelAndView cadastroPagamento(PagamentoModel pagamentoModel){
		ModelAndView mv = new ModelAndView("contasapagar/cadastro");
		return mv;
	}
	
	
	@RequestMapping(value = "pagamento/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid PagamentoModel pagamentoModel, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastroPagamento(pagamentoModel);
		}
		
		pagamentoService.salvar(pagamentoModel);
		attributes.addFlashAttribute("mensagem", "Pagamento salvo com sucesso!");
		return new ModelAndView("redirect:/pagamento/novo");
	}
	
	
//	@RequestMapping("/pagamento")
//	public String pesquisar(){
//		return "contasapagar/index";
//	}
	
	@GetMapping("/pagamento")
	public ModelAndView pesquisar(PagamentoFilter pagamentoFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("contasapagar/index");
		mv.addObject("pagamentos", pagamentoRepository.findAll());
		
//		PageWrapper<PagamentoModel> paginaWrapper = new PageWrapper<>(pagamentoRepository.filtrar(pagamentoFilter, pageable)
//				, httpServletRequest);
//		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}	

}
