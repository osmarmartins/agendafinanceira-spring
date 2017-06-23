package agendafinanceira.controllers.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import agendafinanceira.models.SetorModel;

public class SetorConverter implements Converter<String, SetorModel> {

	@Override
	public SetorModel convert(String id) {
		
		if (!StringUtils.isEmpty(id)){
			SetorModel setor = new SetorModel();
			setor.setIdSetor(Long.valueOf(id));
			return setor;
		}
		
		return null;
	}

}
