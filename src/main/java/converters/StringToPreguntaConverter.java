
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Pregunta;
import repositories.PreguntaRepository;

@Component
@Transactional
public class StringToPreguntaConverter implements Converter<String, Pregunta> {

	@Autowired
	private PreguntaRepository preguntaRepository;


	@Override
	public Pregunta convert(final String string) {
		Pregunta pregunta;
		int id;

		try {
			if (StringUtils.isEmpty(string))
				pregunta = null;
			else {
				id = Integer.valueOf(string);
				pregunta = this.preguntaRepository.findOne(id);
			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return pregunta;
	}
}
