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
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Pregunta;
import services.PreguntaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDeletePregunta extends AbstractTest {

	@Autowired
	private PreguntaService preguntaService;


	@Test
	public void createVotacion() {
		final Object[][] testingData = {
			{
				"6", null
			}, {
				"10", InvalidDataAccessApiUsageException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteVotacion((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void deleteVotacion(final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			final Pregunta pregunta = this.preguntaService.findOne(Integer.valueOf(id));
			this.preguntaService.delete(pregunta);
			Assert.isNull(this.preguntaService.findOne(Integer.valueOf(id)));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
