package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import agendafinanceira.models.FornecedorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoPessoa;
import agendafinanceira.repositories.filters.FornecedorFilter;
import agendafinanceira.services.FornecedorService;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar(FornecedorModel fornecedor){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedor");
		mv.addObject("tipoPessoa", TipoPessoa.values());
		mv.addObject("status", Ativo.values());
		return mv;
	}
	
	
	@GetMapping("/cadastro/{id}")
	public ModelAndView alterar(@PathVariable Long id){
		FornecedorModel fornecedor = fornecedorService.buscarFornecedor(id);
		ModelAndView mv = cadastrar(fornecedor);
		mv.addObject(fornecedor);
		mv.addObject("contatos", fornecedor.getContatos() );
		return mv;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<?> alterarStatus(@PathVariable("id") FornecedorModel fornecedor){
		
		fornecedorService.alterarStatus(fornecedor);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") FornecedorModel fornecedor){
		try {
			fornecedorService.excluir(fornecedor);
		} catch (ExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvar(@Valid FornecedorModel fornecedor, BindingResult result, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return cadastrar(fornecedor);
		}
		
		fornecedorService.salvar(fornecedor);
		
		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedor/cadastro/"+fornecedor.getIdFornecedor());
	}

	
	@GetMapping
	public ModelAndView pesquisar(FornecedorFilter fornecedorFilter, BindingResult result,
			@PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("fornecedor/ListarFornecedor");
		Paginacao<FornecedorModel> paginacao = new Paginacao<>(fornecedorService.filtrar(fornecedorFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}	
	
}
