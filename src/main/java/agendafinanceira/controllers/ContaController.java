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
import agendafinanceira.models.ContaModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.repositories.ContaRepository;
import agendafinanceira.repositories.filters.ContaFilter;
import agendafinanceira.services.ContaService;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService contaService;

	@GetMapping("/cadastro")
	public ModelAndView cadastrar(ContaModel conta){
		ModelAndView mv = new ModelAndView("conta/CadastroConta");
		mv.addObject("tipoAtivo", Ativo.values());
		return mv;
	}
	
	@GetMapping("/cadastro/{id}")
	public ModelAndView alterar(@PathVariable("id") ContaModel conta){
		ModelAndView mv = new ModelAndView("conta/CadastroConta");
		mv.addObject("tipoAtivo", Ativo.values());
		mv.addObject("contaModel", conta);
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvar(@Valid ContaModel conta, BindingResult result, 
			RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return cadastrar(conta);
		}

		try {
			contaService.salvar(conta);
		} catch (Exception e) {
			result.addError(new ObjectError("Error", "Conta já cadastrada"));
			return cadastrar(conta);
		}
		
		attributes.addFlashAttribute("mensagem", "Conta salva com sucesso!");
		return new ModelAndView("redirect:/conta/cadastro");
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") ContaModel conta){
		
		try {
			contaService.excluir(conta);
		} catch (ExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<?> alterarStatus(@PathVariable("id") ContaModel conta){
		
		contaService.alterarStatus(conta);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(ContaFilter contaFilter, BindingResult result,
			@PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("conta/ListarContas");
		Paginacao<ContaModel> paginacao = new Paginacao<>(contaRepository.filtrar(contaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}

}
