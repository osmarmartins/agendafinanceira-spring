package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.controllers.page.Paginacao;
import agendafinanceira.models.FornecedorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoPessoa;
import agendafinanceira.repositories.filters.FornecedorFilter;
import agendafinanceira.services.ContatoService;
import agendafinanceira.services.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ContatoService contatoService;
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar(FornecedorModel fornecedor){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedor");
		mv.addObject("tipoPessoa", TipoPessoa.values());
		mv.addObject("status", Ativo.values());
		mv.addObject("contatos", contatoService.listaContatos(fornecedor.getIdFornecedor()) );
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvar(@Valid FornecedorModel fornecedor, BindingResult result, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return cadastrar(fornecedor);
		}
		
		// TODO salvar fornecedor
		
		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedor/cadastro");
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
