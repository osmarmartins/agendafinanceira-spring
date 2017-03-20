package agendafinanceira.controllers.converters;

import org.springframework.core.convert.converter.Converter;

import agendafinanceira.models.ContaModel;

public class ContaConverter implements Converter<String, ContaModel> {

	@Override
	public ContaModel convert(String id) {
		ContaModel conta = new ContaModel();
		conta.setIdConta(Long.valueOf(id));
		return null;
	}


}
