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
import domain.Respuesta;
import services.RespuestaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestGetRespuesta extends AbstractTest {

	@Autowired
	private RespuestaService respuestaService;


	@Test
	public void getRespuesta() {
		final Object[][] testingData = {
			{
				"7", null
			}, {
				"51", NullPointerException.class
			}, {
				"8", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.getpregunta((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void getpregunta(final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			final Respuesta r = this.respuestaService.findOne(Integer.valueOf(id));
			final RespuestaApi vapi = new RespuestaApi();

			vapi.setPregunta(r.getPregunta().getId());
			vapi.setTexto_respuesta(r.getTexto_respuesta());

			Assert.notNull(r);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
