package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Pregunta;
import repositories.PreguntaRepository;

@Service
@Transactional
public class PreguntaService {

	@Autowired
	private PreguntaRepository preguntaRepository;


	public Pregunta create() {
		final Pregunta res = new Pregunta();
		return res;
	}

	public Collection<Pregunta> findAll() {
		Collection<Pregunta> result;
		result = this.preguntaRepository.findAll();
		return result;
	}

	public Collection<Pregunta> listAll() {
		Collection<Pregunta> result;
		result = this.preguntaRepository.findAll();
		return result;
	}

	public Pregunta findOne(final int id) {
		Pregunta result;
		result = this.preguntaRepository.findOne(id);
		return result;
	}

	public Pregunta save(final Pregunta pregunta) {

		return this.preguntaRepository.save(pregunta);
	}
}
