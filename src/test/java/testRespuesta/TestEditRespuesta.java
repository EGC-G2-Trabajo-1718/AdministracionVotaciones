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
public class TestEditRespuesta extends AbstractTest {

	@Autowired
	private PreguntaService		preguntaService;
	@Autowired
	private RespuestaService	respuestaService;


	@Test
	public void editRespuesta() {
		final Object[][] testingData = {
			{
				"7", "3", "Texto respuesta", true, null
			}, {
				"8", "3", "Texto respuesta 2", false, null
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.editRespuesta((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Boolean) testingData[i][3], (Class<?>) testingData[i][4]);
	}

	protected void editRespuesta(final String id, final String pregunta, final String texto_respuesta, final Boolean seleccionada, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final RespuestaApi vapi = new RespuestaApi();
			Respuesta respuesta = this.respuestaService.findOne(Integer.valueOf(id));
			Pregunta p = this.preguntaService.findOne(Integer.valueOf(pregunta));
			if (texto_respuesta != null) {
				vapi.setTexto_respuesta(texto_respuesta);
				respuesta.setTexto_respuesta(texto_respuesta);
			}
			if (pregunta != null) {
				vapi.setPregunta(Integer.valueOf(pregunta));
				respuesta.setPregunta(p);

			}
			if (seleccionada != null) {
				vapi.setSeleccionada(seleccionada);
				respuesta.setSeleccionada(seleccionada);
			}
			p.getRespuestas().add(respuesta);
			p = this.preguntaService.save(p);
			respuesta = this.respuestaService.save(respuesta);
			Assert.isTrue(p.getRespuestas().contains(respuesta));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
