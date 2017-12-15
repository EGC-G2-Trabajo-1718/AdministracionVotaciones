
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Respuesta;
import repositories.RespuestaRepository;

@Service
@Transactional
public class RespuestaService {

	@Autowired
	private RespuestaRepository respuestaRepository;


	public Respuesta create() {
		final Respuesta res = new Respuesta();
		return res;
	}

	public Collection<Respuesta> findAll() {
		Collection<Respuesta> result;
		result = this.respuestaRepository.findAll();
		return result;
	}

	public Collection<Respuesta> listAll() {
		Collection<Respuesta> result;
		result = this.respuestaRepository.findAll();
		return result;
	}

	public Respuesta findOne(final int id) {
		Respuesta result;
		result = this.respuestaRepository.findOne(id);
		return result;
	}

	public Respuesta save(final Respuesta respuesta) {

		return this.respuestaRepository.save(respuesta);
	}

	public void delete(final Respuesta respuesta) {

		this.respuestaRepository.delete(respuesta);
	}

}
