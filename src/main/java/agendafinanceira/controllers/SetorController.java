package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.controllers.page.Paginacao;
import agendafinanceira.models.SetorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.SetorRepository;
import agendafinanceira.repositories.filters.SetorFilter;
import agendafinanceira.services.SetorService;

@Controller
@RequestMapping("/setor")
public class SetorController {

	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;

	
	@RequestMapping("/manutencao")
	public ModelAndView cadastroSetor(SetorModel setorModel){
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		mv.addObject("tipoAtivo", Ativo.values());
		return mv;
	}
	

	@RequestMapping(value = "/manutencao", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid SetorModel setorModel, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return cadastroSetor(setorModel);
		}

		try {
			setorService.salvar(setorModel);
		} catch (Exception e) {
			result.addError(new ObjectError("Error", "Setor já cadastrado"));
			return cadastroSetor(setorModel);
		}
		
		attributes.addFlashAttribute("mensagem", "Setor salvo com sucesso!");
		return new ModelAndView("redirect:/setor/manutencao");
	}
	

	
	@RequestMapping(value = "/manutencao", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid SetorModel setorModel, BindingResult result) {
		
		System.out.println();
		
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		
		setorModel = setorService.salvar(setorModel);

		return ResponseEntity.ok(setorModel);
	}	
	
	@GetMapping
	public ModelAndView pesquisar(SetorFilter setorFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("setor/ListarSetores");
		Paginacao<SetorModel> paginacao = new Paginacao<>(setorRepository.filtrar(setorFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}
	
}
