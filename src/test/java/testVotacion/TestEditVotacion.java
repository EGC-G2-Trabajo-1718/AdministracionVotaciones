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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.VotacionApi;
import domain.Votacion;
import services.VotacionService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestEditVotacion extends AbstractTest {

	@Autowired
	private VotacionService votacionService;


	@Test
	public void createVotacion() {
		final Object[][] testingData = {
			{
				null, null, "Votacion test edit 1", "Descripcion test edit 1", "10/10/17 10:10", "10/10/17 10:10", "clave1", "1", null
			}, {
				null, null, null, null, null, null, null, "1", null
			}, {
				"6", "9", "Votacion test edit 2", "Descripcion test edit 2", "hola", "18/09/17 10:10", "clave3", "1", ParseException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.editVotacion((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (String) testingData[i][7],
				(Class<?>) testingData[i][8]);
	}

	protected void editVotacion(final String id_censo, final String id_grupo, final String titulo, final String descripcion, final String fecha_ini, final String fecha_fin, final String clave, final String id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final VotacionApi vapi = new VotacionApi();
			final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			Votacion votacion = this.votacionService.findOne(Integer.valueOf(id));
			if (fecha_fin != null)
				votacion.setFecha_fin(formatter.parse(fecha_fin));

			if (fecha_ini != null)
				votacion.setFecha_ini(formatter.parse(fecha_ini));

			if (id_grupo != null)
				votacion.setId_censo(Integer.valueOf(id_censo));

			if (id_grupo != null)
				votacion.setId_grupo(Integer.valueOf(id_grupo));

			if (titulo != null)
				votacion.setTitulo(titulo);

			if (clave != null)
				votacion.setClave(clave);

			if (descripcion != null)
				votacion.setDescripcion(descripcion);
			votacion = this.votacionService.save(votacion);

			if (fecha_fin == null)
				vapi.setFecha_fin(formatter.format(votacion.getFecha_fin()));
			else
				vapi.setFecha_fin(fecha_fin);

			if (fecha_ini == null)
				vapi.setFecha_ini(formatter.format(votacion.getFecha_ini()));
			else
				vapi.setFecha_ini(fecha_ini);

			if (id_censo == null)
				vapi.setId_censo(votacion.getId_censo());
			else
				vapi.setId_censo(Integer.valueOf(id_censo));

			if (id_grupo == null)
				vapi.setId_grupo(votacion.getId_grupo());
			else
				vapi.setId_grupo(Integer.valueOf(id_grupo));

			if (titulo == null)
				vapi.setTitulo(votacion.getTitulo());
			else
				vapi.setTitulo(titulo);

			if (clave == null)
				vapi.setClave(votacion.getClave());
			else
				vapi.setClave(clave);

			if (descripcion == null)
				vapi.setDescripcion(votacion.getDescripcion());
			else
				vapi.setDescripcion(descripcion);

			vapi.setId(Integer.valueOf(id));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
