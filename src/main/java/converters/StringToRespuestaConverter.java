
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Respuesta;
import repositories.RespuestaRepository;

@Component
@Transactional
public class StringToRespuestaConverter implements Converter<String, Respuesta> {

	@Autowired
	private RespuestaRepository respuestaRepository;


	@Override
	public Respuesta convert(final String string) {
		Respuesta respuesta;
		int id;

		try {
			if (StringUtils.isEmpty(string))
				respuesta = null;
			else {
				id = Integer.valueOf(string);
				respuesta = this.respuestaRepository.findOne(id);
			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return respuesta;
	}
}
