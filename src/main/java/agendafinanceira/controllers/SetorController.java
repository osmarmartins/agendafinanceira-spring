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

import agendafinanceira.models.SetorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.SetorRepository;
import agendafinanceira.repositories.filters.SetorFilter;
import agendafinanceira.services.SetorService;
import agendafinanceira.utils.PageWrapper;

@Controller
@RequestMapping("/setor")
public class SetorController {

	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;

	
	@RequestMapping("/novo")
	public ModelAndView cadastroSetor(SetorModel setorModel){
		ModelAndView mv = new ModelAndView("setor/cadastro");
		mv.addObject("tipoAtivo", Ativo.values());
		System.out.println("(get)Setor: " + setorModel.toString());
		return mv;
	}
	

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid SetorModel setorModel, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return cadastroSetor(setorModel);
		}

		System.out.println("(post)Setor: " + setorModel.toString());
		
		setorService.salvar(setorModel);
		attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso!");
		return new ModelAndView("redirect:/setor/novo");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(SetorFilter setorFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("setor/index");
		mv.addObject("setores", setorRepository.findAll());
		
//		PageWrapper<SetorModel> paginaWrapper = new PageWrapper<>(setorRepository.filtrar(setorFilter, pageable)
//				, httpServletRequest);
//		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
}
