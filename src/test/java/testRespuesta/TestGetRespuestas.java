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

import java.util.ArrayList;

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
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestGetRespuestas extends AbstractTest {

	@Autowired
	private PreguntaService preguntaService;


	@Test
	public void getPreguntas() {
		final Object[][] testingData = {
			{
				"2", null
			}, {
				"0", NullPointerException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.getPreguntas((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void getPreguntas(final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final Pregunta p = this.preguntaService.findOne(Integer.valueOf(id));

			final ArrayList<RespuestaApi> lista = new ArrayList<RespuestaApi>();
			for (final Respuesta r : p.getRespuestas()) {
				final RespuestaApi aux = new RespuestaApi();
				aux.setPregunta(r.getPregunta().getId());
				aux.setTexto_respuesta(r.getTexto_respuesta());
				lista.add(aux);
			}

			Assert.notEmpty(lista);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
