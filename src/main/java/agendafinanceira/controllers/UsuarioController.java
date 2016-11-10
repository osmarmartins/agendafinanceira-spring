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

import agendafinanceira.models.UsuarioModel;
import agendafinanceira.models.enums.TipoUsuario;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.repositories.filters.UsuarioFilter;
import agendafinanceira.services.UsuarioService;
import agendafinanceira.utils.PageWrapper;

@Controller
public class UsuarioController {
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@RequestMapping("login")
	public String loginUsuario(){
		return "usuario/login";
	}

	@RequestMapping("usuario/cadastro")
	public ModelAndView cadastroUsuario(UsuarioModel usuarioModel){
		ModelAndView mv = new ModelAndView("usuario/cadastroUsuario");
		mv.addObject("credenciais", TipoUsuario.values());
		return mv;
	}
	
	
	@RequestMapping(value = "usuario/casdastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid UsuarioModel usuarioModel, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return cadastroUsuario(usuarioModel);
		}
		
		usuarioService.salvar(usuarioModel);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/usuario/cadastro");
	}
	
	
	@GetMapping("/usuario")
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("usuario/pesquisaUsuario");
		mv.addObject("usuarios", usuarioRepository.findAll());
		
//		PageWrapper<UsuarioModel> paginaWrapper = 
//				new PageWrapper<>(usuarioRepository.filtrar(usuarioFilter, pageable), httpServletRequest);
//		mv.addObject("pagina", paginaWrapper);
		
		
		return mv;
	}

}
