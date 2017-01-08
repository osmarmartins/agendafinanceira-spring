package agendafinanceira.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendafinanceira.controllers.page.Paginacao;
import agendafinanceira.models.UsuarioModel;
import agendafinanceira.models.enums.Ativo;
import agendafinanceira.models.enums.TipoUsuario;
import agendafinanceira.repositories.UsuarioRepository;
import agendafinanceira.repositories.filters.UsuarioFilter;
import agendafinanceira.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/login")
	public String loginUsuario() {
		return "usuario/login";
	}

	@RequestMapping("/cadastro")
	public ModelAndView novo(UsuarioModel usuarioModel) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("credenciais", TipoUsuario.values());
		mv.addObject("tipoAtivo", Ativo.values());
		return mv;
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid UsuarioModel usuarioModel, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return novo(usuarioModel);
		}

		try {
			usuarioService.salvar(usuarioModel);
		} catch (Exception e) {
			bindingResult.addError(new ObjectError("Error", e.getMessage()));
			return novo(usuarioModel);
		}

		redirectAttributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");

		return new ModelAndView("redirect:/usuario/cadastro");
	}

	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {

		ModelAndView mv = new ModelAndView("/usuario/ListarUsuarios");
		Paginacao<UsuarioModel> paginacao = new Paginacao<>(usuarioRepository.filtrar(usuarioFilter, pageable),	httpServletRequest);
		mv.addObject("pagina", paginacao);

		return mv;
	}

}
