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
import domain.Votacion;
import services.VotacionService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestGetPreguntas extends AbstractTest {

	@Autowired
	private VotacionService votacionService;


	@Test
	public void getPreguntas() {
		final Object[][] testingData = {
			{
				"1", null
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
			final Votacion v = this.votacionService.findOne(Integer.valueOf(id));

			final ArrayList<PreguntaApi> lista = new ArrayList<PreguntaApi>();
			for (final Pregunta p : v.getPreguntas()) {
				final PreguntaApi vapi = new PreguntaApi();
				vapi.setId(p.getId());
				if (p.getDependencia() != null)
					vapi.setId_dependencia(p.getDependencia().getId());
				vapi.setId_votacion(p.getVotacion().getId());
				vapi.setTexto_pregunta(p.getTexto_pregunta());
				vapi.setTipo_pregunta(p.getTipo_pregunta());
				final ArrayList<Integer> listaux = new ArrayList<Integer>();
				if (p.getRespuestas() != null || !p.getRespuestas().isEmpty())
					for (final Respuesta r : p.getRespuestas())
						listaux.add(r.getId());

				vapi.setId_respuestas(listaux.toString());

				lista.add(vapi);
			}

			Assert.notEmpty(lista);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
