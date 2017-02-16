package agendafinanceira.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.models.FornecedorModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoPessoa;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	@GetMapping("/cadastro")
	public ModelAndView cadastrar(FornecedorModel fornecedor){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedor");
		mv.addObject("tipoPessoa", TipoPessoa.values());
		mv.addObject("status", Ativo.values());
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
	public ModelAndView pesquisar(){
		return new ModelAndView("fornecedor/ListarFornecedor");
	}
	
	
	
}
