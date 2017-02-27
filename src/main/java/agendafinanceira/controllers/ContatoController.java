package agendafinanceira.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.models.ContatoModel;
import agendafinanceira.models.FornecedorModel;
import agendafinanceira.services.ContatoService;

@Controller
@RequestMapping("/fornecedor/contato")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;
	
	@GetMapping("/{idFornecedor}")
	public ModelAndView cadastrar(@PathVariable Long idFornecedor, ContatoModel contato){
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedorContato");
		mv.addObject("idFornecedor", idFornecedor);
		return mv;
	}

	@GetMapping("/cadastro/{idContato}")
	public ModelAndView alterar(@PathVariable("idContato") ContatoModel contato){
		ModelAndView mv = cadastrar(contato.getFornecedor().getIdFornecedor(), contato);
		mv.addObject("contatoModel", contato);
		return mv;
	}
	
	@PostMapping("/{idFornecedor}")
	public ModelAndView salvar(@PathVariable("idFornecedor") FornecedorModel fornecedor, @Valid ContatoModel contato, BindingResult result, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return cadastrar(contato.getFornecedor().getIdFornecedor(), contato);
		}
		
		contatoService.salvar(fornecedor, contato);
		
		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedor/contato/"+contato.getFornecedor().getIdFornecedor());
	}
	

}
