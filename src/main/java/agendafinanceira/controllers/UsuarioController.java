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
import agendafinanceira.models.UsuarioModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoUsuario;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.repositories.filters.UsuarioFilter;
import agendafinanceira.services.UsuarioService;
import agendafinanceira.services.exception.ExcluirEntidadeException;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/login")
	public String acessar() {
		return "usuario/login";
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastrar(UsuarioModel usuario){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("credenciais", TipoUsuario.values());
		mv.addObject("tipoAtivo", Ativo.values());
		return mv;
	}
	
	@GetMapping("/cadastro/{id}")
	public ModelAndView alterar(@PathVariable("id") UsuarioModel usuario){
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("credenciais", TipoUsuario.values());
		mv.addObject("tipoAtivo", Ativo.values());
		mv.addObject("usuarioModel", usuario);
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvar(@Valid UsuarioModel usuario, BindingResult result, 
			RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return cadastrar(usuario);
		}

		try {
			usuarioService.salvar(usuario);
		} catch (Exception e) {
			result.addError(new ObjectError("Error", "Usuário já cadastrado"));
			return cadastrar(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/usuario/cadastro");
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") UsuarioModel usuario){
		
		try {
			usuarioService.excluir(usuario);
		} catch (ExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<?> alterarStatus(@PathVariable("id") UsuarioModel usuario){
		
		usuarioService.alterarStatus(usuario);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result,
			@PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("usuario/ListarUsuarios");
		Paginacao<UsuarioModel> paginacao = new Paginacao<>(usuarioRepository.filtrar(usuarioFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginacao);
		
		return mv;
	}
	
}
