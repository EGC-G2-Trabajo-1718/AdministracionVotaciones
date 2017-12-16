
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Votacion;
import repositories.VotacionRepository;

@Component
@Transactional
public class StringToVotacionConverter implements Converter<String, Votacion> {

	@Autowired
	private VotacionRepository votacionRepository;


	@Override
	public Votacion convert(final String string) {
		Votacion votacion;
		int id;

		try {
			if (StringUtils.isEmpty(string))
				votacion = null;
			else {
				id = Integer.valueOf(string);
				votacion = this.votacionRepository.findOne(id);
			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return votacion;
	}
}
