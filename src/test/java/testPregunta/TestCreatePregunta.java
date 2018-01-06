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

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import api.PreguntaApi;
import domain.Pregunta;
import services.PreguntaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestCreatePregunta extends AbstractTest {

	@Autowired
	private PreguntaService preguntaService;


	@Test
	public void createPregunta() {
		final Object[][] testingData = {
			{
				"Texto pregunta", "Tipo pregunta", "3", null
			}, {
				"Texto pregunta", "Tipo pregunta", "1", IllegalArgumentException.class
			}, {
				"Texto pregunta", "Tipo pregunta", "3", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearPregunta((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void crearPregunta(final String texto_pregunta, final String tipo_pregunta, final String dependencia, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final PreguntaApi vapi = new PreguntaApi();

			vapi.setTexto_pregunta(texto_pregunta);
			vapi.setTipo_pregunta(tipo_pregunta);
			vapi.setId_dependencia(Integer.valueOf(dependencia));
			Pregunta pregunta = new Pregunta();
			pregunta.setTexto_pregunta(texto_pregunta);
			pregunta.setTipo_pregunta(tipo_pregunta);
			pregunta.setDependencia(this.preguntaService.findOne(Integer.valueOf(dependencia)));
			pregunta = this.preguntaService.save(pregunta);
			vapi.setId(pregunta.getId());
			Assert.isTrue(vapi.getId() != 0);
			Assert.notNull(pregunta.getDependencia());
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
