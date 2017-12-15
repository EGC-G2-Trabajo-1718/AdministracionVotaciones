
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import api.DeleteApi;
import api.VotacionApi;
import domain.Pregunta;
import domain.Votacion;
import services.VotacionService;

@Controller
@RequestMapping("/api")
public class VotacionController extends AbstractController {

	@Autowired
	private VotacionService votacionService;


	public VotacionController() {
		super();
	}

	@RequestMapping(value = "/get/votacion", method = RequestMethod.GET)
	public @ResponseBody VotacionApi ObtenerVotacion(@RequestParam(value = "id", required = true) final int id) {
		final Votacion v = this.votacionService.findOne(id);
		final VotacionApi vapi = new VotacionApi();
		vapi.setDescripcion(v.getDescripcion());
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		vapi.setFecha_fin(formatter.format(v.getFecha_ini()));
		vapi.setFecha_ini(formatter.format(v.getFecha_ini()));
		vapi.setId(v.getId());
		vapi.setId_censo(v.getId_censo());
		vapi.setId_grupo(v.getId_grupo());
		vapi.setTitulo(v.getTitulo());
		vapi.setClave(v.getClave());

		final ArrayList<Integer> lista = new ArrayList<Integer>();
		if (v.getPreguntas() != null || !v.getPreguntas().isEmpty())
			for (final Pregunta p : v.getPreguntas())
				lista.add(p.getId());
		vapi.setId_preguntas(lista.toString());
		return vapi;
	}

	@RequestMapping(value = "/post/votacionCreate", method = RequestMethod.POST)
	public @ResponseBody VotacionApi crearVotacion(@RequestParam(value = "id_censo", required = true) final int id_censo, @RequestParam(value = "id_grupo", required = true) final int id_grupo,
		@RequestParam(value = "titulo", required = true) final String titulo, @RequestParam(value = "descripcion", required = true) final String descripcion, @RequestParam(value = "fecha_ini", required = true) final String fecha_ini,
		@RequestParam(value = "fecha_fin", required = true) final String fecha_fin, @RequestParam(value = "clave", required = true) final String clave) throws ParseException {
		final VotacionApi vapi = new VotacionApi();
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		vapi.setFecha_fin(fecha_fin);
		vapi.setFecha_ini(fecha_ini);
		vapi.setId_censo(id_censo);
		vapi.setId_grupo(id_grupo);
		vapi.setTitulo(titulo);
		vapi.setClave(clave);
		vapi.setDescripcion(descripcion);

		Votacion votacion = new Votacion();
		votacion.setFecha_fin(formatter.parse(fecha_fin));
		votacion.setFecha_ini(formatter.parse(fecha_ini));
		votacion.setId_censo(id_censo);
		votacion.setId_grupo(id_grupo);
		votacion.setTitulo(titulo);
		votacion.setClave(clave);
		votacion.setDescripcion(descripcion);

		votacion = this.votacionService.save(votacion);
		vapi.setId(votacion.getId());
		return vapi;
	}

	@RequestMapping(value = "/post/votacionEdit", method = RequestMethod.POST)
	public @ResponseBody VotacionApi editarVotación(@RequestBody(required = false) final Integer id, @RequestBody(required = false) final Integer id_censo, @RequestBody(required = true) final Integer id_grupo,
		@RequestBody(required = false) final String titulo, @RequestBody(required = false) final String descripcion, @RequestBody(required = false) final String fecha_ini, @RequestBody(required = false) final String fecha_fin,
		@RequestBody(required = false) final String clave) throws ParseException {
		final VotacionApi vapi = new VotacionApi();
		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Votacion votacion = this.votacionService.findOne(id);
		if (fecha_fin != null)
			votacion.setFecha_fin(formatter.parse(fecha_fin));

		if (fecha_ini != null)
			votacion.setFecha_ini(formatter.parse(fecha_ini));

		if (id_grupo != null)
			votacion.setId_censo(id_censo);

		if (id_grupo != null)
			votacion.setId_grupo(id_grupo);

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
			vapi.setId_censo(id_censo);

		if (id_grupo == null)
			vapi.setId_grupo(votacion.getId_grupo());
		else
			vapi.setId_grupo(id_grupo);

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

		vapi.setId(id);
		return vapi;
	}

	@RequestMapping(value = "/post/votacionDelete", method = RequestMethod.POST)
	public @ResponseBody DeleteApi eliminarVotacion(@RequestParam(value = "id", required = true) final int id) {
		final DeleteApi vapi = new DeleteApi();

		try {
			this.votacionService.delete(this.votacionService.findOne(id));
			vapi.setExito(true);
			vapi.setMensaje("Eliminado con éxito");
		} catch (final IllegalArgumentException e) {
			vapi.setExito(false);
			vapi.setMensaje("Error al eliminar");
		}

		return vapi;
	}

}
