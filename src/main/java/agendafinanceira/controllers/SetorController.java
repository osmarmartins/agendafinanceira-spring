package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.controllers.page.Paginacao;
import agendafinanceira.models.SetorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.SetorRepository;
import agendafinanceira.repositories.filters.SetorFilter;
import agendafinanceira.services.SetorService;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Controller
@RequestMapping("/setor")
public class SetorController {
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;

	@GetMapping("/cadastro")
	public ModelAndView cadastroSetor(SetorModel setorModel){
//	public ModelAndView cadastroSetor(){
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		mv.addObject("tipoAtivo", Ativo.values());
		return mv;
	}
	
	@GetMapping("/cadastro/{id}")
	public ModelAndView cadastroSetor(@PathVariable("id") Long id, SetorModel setorModel){
//	public ModelAndView cadastroSetor(@PathVariable("idSetor") SetorModel setorModel){
		setorModel = setorRepository.findOne(id);
		ModelAndView mv = new ModelAndView("setor/CadastroSetor");
		mv.addObject("tipoAtivo", Ativo.values());
		mv.addObject("setorModel", setorModel);
		
		System.out.println(setorModel.toString());
		
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvar(@Valid SetorModel setorModel, BindingResult result, 
			RedirectAttributes attributes) {
		
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
		return new ModelAndView("redirect:/setor/cadastro");
	}
	
	
	
	@GetMapping
	public ModelAndView pesquisar(SetorFilter setorFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("setor/ListarSetores");
		Paginacao<SetorModel> paginacao = new Paginacao<>(setorRepository.filtrar(setorFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}

	
	@DeleteMapping("/{idSetor}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("idSetor") SetorModel setor){
		
		try {
			setorService.excluir(setor);
		} catch (ExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/{idSetor}")
	public @ResponseBody ResponseEntity<?> alterarStatus(@PathVariable("idSetor") SetorModel setor){
		
		setorService.alterarStatus(setor);
		
		return ResponseEntity.ok().build();
	}

}
