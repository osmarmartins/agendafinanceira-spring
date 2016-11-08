package agendafinanceira.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import agendafinanceira.services.UsuarioService;

@Configuration
@ComponentScan(basePackageClasses = UsuarioService.class)
public class ServiceConfig {

	
}
