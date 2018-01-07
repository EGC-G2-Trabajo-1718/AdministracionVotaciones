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

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestCreateVotacion extends AbstractTest {

	@Autowired
	private VotacionService votacionService;


	@Test
	public void createVotacion() {
		final Object[][] testingData = {
			{
				"8", "7", "Votacion test 1", "Descripcion test 1", "10/10/17 10:10", "10/10/17 10:10", "clave1", null
			}, {
				"3", "2", "Votacion test 2", "Descripcion test 2", "13/09/17 11:11", "18/09/17 10:10", "clave2", null
			}, {
				"3", "2", "Votacion test 2", "Descripcion test 2", "13/09/17 11:11", "18/09/17 10:10", "clave3", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearVotaciones((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}

	protected void crearVotaciones(final String id_censo, final String id_grupo, final String titulo, final String descripcion, final String fecha_ini, final String fecha_fin, final String clave, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Votacion votacion = new Votacion();
			votacion.setFecha_fin(formatter.parse(fecha_fin));
			votacion.setFecha_ini(formatter.parse(fecha_ini));
			votacion.setId_censo(Integer.valueOf(id_censo));
			votacion.setId_grupo(Integer.valueOf(id_grupo));
			votacion.setTitulo(titulo);
			votacion.setClave(clave);
			votacion.setDescripcion(descripcion);

			if (clave != "clave3") {
				votacion = this.votacionService.save(votacion);
				Assert.isTrue(votacion.getId() != 0);
			} else
				Assert.isTrue(votacion.getId() != 0);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
