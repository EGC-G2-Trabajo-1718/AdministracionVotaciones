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
import services.RespuestaService;
import services.VotacionService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestEditPregunta extends AbstractTest {

	@Autowired
	private PreguntaService		preguntaService;
	@Autowired
	private VotacionService		votacionService;
	@Autowired
	private RespuestaService	respuestaService;


	@Test
	public void editPregunta() {
		final Object[][] testingData = {
			{
				"3", "Texto pregunta", "Tipo pregunta", "1", "3", "[4,5]", IllegalArgumentException.class
			}, {
				"5", "Texto pregunta", "Tipo pregunta", "1", "3", "[6,7]", IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.editarPregunta((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	protected void editarPregunta(final String id, final String texto_pregunta, final String tipo_pregunta, final String votacion, final String dependencia, final String respuestas, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		final int idvot = Integer.valueOf(votacion);
		try {
			Pregunta pregunta = this.preguntaService.findOne(Integer.valueOf(id));
			final PreguntaApi vapi = new PreguntaApi();
			if (texto_pregunta != null) {
				pregunta.setTexto_pregunta(texto_pregunta);
				vapi.setTexto_pregunta(texto_pregunta);
			}
			if (tipo_pregunta != null) {
				pregunta.setTipo_pregunta(tipo_pregunta);
				vapi.setTipo_pregunta(tipo_pregunta);
			}
			if (votacion != null) {
				pregunta.setVotacion(this.votacionService.findOne(idvot));
				vapi.setId_votacion(Integer.valueOf(votacion));
			}
			if (dependencia != null) {
				pregunta.setDependencia(this.preguntaService.findOne(idvot));
				vapi.setId_dependencia(Integer.valueOf(dependencia));
			}
			final ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
			String resvapi = "[";
			if (respuestas != null)
				for (int i = 0; i == respuestas.length(); i++) {
					if (respuestas.charAt(i) != '[' && respuestas.charAt(i) != ',' && respuestas.charAt(i) != ']') {
						resp.add(this.respuestaService.findOne(i));
						resvapi.concat(i + ",");
					}

					pregunta.setRespuestas(resp);
					resvapi = resvapi.substring(0, resvapi.length() - 2);
					resvapi.concat("]");
					vapi.setId_respuestas(resvapi);
				}
			for (final Respuesta r : resp) {
				r.setPregunta(pregunta);
				this.respuestaService.save(r);
			}
			pregunta = this.preguntaService.save(pregunta);
			for (final Respuesta r : pregunta.getRespuestas())
				Assert.isTrue(respuestas.contains(Integer.valueOf(r.getId()).toString()));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
