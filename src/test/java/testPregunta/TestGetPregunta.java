/*
 * SampleTest.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package testPregunta;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import api.PreguntaApi;
import domain.Pregunta;
import domain.Respuesta;
import services.PreguntaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestGetPregunta extends AbstractTest {

	@Autowired
	private PreguntaService preguntaService;


	@Test
	public void getpregunta() {
		final Object[][] testingData = {
			{
				"2", null
			}, {
				"0", NullPointerException.class
			}, {
				"3", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.getpregunta((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void getpregunta(final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			final Pregunta p = this.preguntaService.findOne(Integer.valueOf(id));
			final PreguntaApi vapi = new PreguntaApi();

			if (p.getDependencia() != null)
				vapi.setId_dependencia(p.getDependencia().getId());
			vapi.setTexto_pregunta(p.getTexto_pregunta());
			vapi.setTipo_pregunta(p.getTipo_pregunta());
			vapi.setId_votacion(p.getId());
			vapi.setId(p.getId());
			final ArrayList<Integer> lista = new ArrayList<Integer>();
			if (p.getRespuestas() != null || !p.getRespuestas().isEmpty())
				for (final Respuesta r : p.getRespuestas())
					lista.add(r.getId());

			vapi.setId_respuestas(lista.toString());

			Assert.notNull(p);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
