/*
 * SampleTest.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package testVotacion;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Votacion;
import services.VotacionService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDeleteVotacion extends AbstractTest {

	@Autowired
	private VotacionService votacionService;


	@Test
	public void createVotacion() {
		final Object[][] testingData = {
			{
				"1", null
			}, {
				"0", InvalidDataAccessApiUsageException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteVotacion((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void deleteVotacion(final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			final Votacion votacion = this.votacionService.findOne(Integer.valueOf(id));
			this.votacionService.delete(votacion);
			Assert.isNull(this.votacionService.findOne(Integer.valueOf(id)));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
