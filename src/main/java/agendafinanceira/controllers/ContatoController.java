package agendafinanceira.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import agendafinanceira.models.ContatoModel;

@Controller
@RequestMapping("/fornecedor/contato")
public class ContatoController {
	
	@GetMapping("/{idFornecedor}")
	public ModelAndView cadastrar(@PathVariable Long idFornecedor, ContatoModel contato){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedorContato");
		mv.addObject("idFornecedor", idFornecedor);
		return mv;
	}

	@GetMapping("/cadastro/{idContato}")
	public ModelAndView alterar(@PathVariable("idContato") ContatoModel contato){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedorContato");
		mv.addObject("contatoModel", contato);
		return mv;
	}

}
