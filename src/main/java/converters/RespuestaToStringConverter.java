package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import domain.Respuesta;

@Component
@Transactional
public class RespuestaToStringConverter implements Converter<Respuesta, String> {

	@Override
	public String convert(final Respuesta arg0) {
		Assert.notNull(arg0);

		String string;
		string = String.valueOf(arg0.getId());

		return string;
	}
}
