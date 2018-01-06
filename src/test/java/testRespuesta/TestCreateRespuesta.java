/*
 * SampleTest.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package testRespuesta;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import api.RespuestaApi;
import domain.Pregunta;
import domain.Respuesta;
import services.PreguntaService;
import services.RespuestaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCreateRespuesta extends AbstractTest {

	@Autowired
	private PreguntaService		preguntaService;
	@Autowired
	private RespuestaService	respuestaService;


	@Test
	public void createRespuesta() {
		final Object[][] testingData = {
			{
				"3", "Texto respuesta", null
			}, {
				"1", "Texto respuesta 2", IllegalArgumentException.class
			}, {
				"e", "Texto respuesta 3", NumberFormatException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearRespuesta((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void crearRespuesta(final String pregunta, final String texto_respuesta, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final RespuestaApi vapi = new RespuestaApi();
			Respuesta respuesta = new Respuesta();
			final Pregunta p = this.preguntaService.findOne(Integer.valueOf(pregunta));
			Assert.notNull(p);
			respuesta.setPregunta(p);
			vapi.setPregunta(Integer.valueOf(pregunta));
			vapi.setSeleccionada(false);
			respuesta.setSeleccionada(false);
			respuesta.setTexto_respuesta(texto_respuesta);
			vapi.setTexto_respuesta(texto_respuesta);
			respuesta = this.respuestaService.save(respuesta);
			Assert.isTrue(respuesta.getId() != 0);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
