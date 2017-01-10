package agendafinanceira.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import agendafinanceira.models.enums.Ativo;

public class AtivoConverter implements Converter<String, Ativo> {

	@Override
	public Ativo convert(String source) {
		Ativo ativo = Ativo.INATIVO;
		if (!StringUtils.isEmpty(source)){
			if (source.equals("true")) {
				ativo = Ativo.ATIVO;
			} 
		}
		return ativo;
	}

}
