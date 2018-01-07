
package controllers;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import api.DeleteApi;
import api.PreguntaApi;
import domain.Pregunta;
import domain.Respuesta;
import domain.Votacion;
import services.PreguntaService;
import services.RespuestaService;
import services.VotacionService;

@Controller
@RequestMapping("/api")
public class PreguntaController extends AbstractController {

	@Autowired
	private PreguntaService		preguntaService;

	@Autowired
	private RespuestaService	respuestaService;

	@Autowired
	private VotacionService		votacionService;


	public PreguntaController() {
		super();
	}

	@RequestMapping(value = "/get/pregunta", method = RequestMethod.GET)
	public @ResponseBody PreguntaApi ObtenerPregunta(@RequestParam(value = "id", required = true) final int id) {
		final Pregunta p = this.preguntaService.findOne(id);
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
		return vapi;
	}

	@RequestMapping(value = "/get/preguntas", method = RequestMethod.GET)
	public @ResponseBody ArrayList<PreguntaApi> ObtenerPreguntas(@RequestParam(value = "id", required = true) final int id) {
		final Votacion v = this.votacionService.findOne(id);

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

		return lista;
	}

	@RequestMapping(value = "/post/preguntaCreate", method = RequestMethod.POST)
	public @ResponseBody PreguntaApi crearPregunta(@RequestParam(value = "texto_pregunta", required = true) final String texto_pregunta, @RequestParam(value = "tipo_pregunta", required = true) final String tipo_pregunta) throws ParseException {
		final PreguntaApi vapi = new PreguntaApi();

		vapi.setTexto_pregunta(texto_pregunta);
		vapi.setTipo_pregunta(tipo_pregunta);

		Pregunta pregunta = new Pregunta();
		pregunta.setTexto_pregunta(texto_pregunta);
		pregunta.setTipo_pregunta(tipo_pregunta);

		pregunta = this.preguntaService.save(pregunta);
		vapi.setId(pregunta.getId());
		return vapi;
	}

	@RequestMapping(value = "/post/preguntaEdit", method = RequestMethod.POST)
	public @ResponseBody PreguntaApi editarPregunta(@RequestBody(required = true) final Integer id, @RequestBody(required = false) final String texto_pregunta, @RequestBody(required = false) final String tipo_pregunta,
		@RequestBody(required = false) final Integer votacion, @RequestBody(required = false) final Integer dependencia, @RequestBody(required = false) final String respuestas) throws ParseException {

		final Pregunta pregunta = this.preguntaService.findOne(id);
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
			pregunta.setVotacion(this.votacionService.findOne(id));
			vapi.setId_votacion(votacion);
		}
		if (dependencia != null) {
			pregunta.setDependencia(this.preguntaService.findOne(id));
			vapi.setId_dependencia(dependencia);
		}
		final ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
		String resvapi = "[";
		if (respuestas != null)
			for (int i = 0; i == 1; i++) {
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
		this.preguntaService.save(pregunta);

		return vapi;
	}

	@RequestMapping(value = "/post/preguntaDelete", method = RequestMethod.POST)
	public @ResponseBody DeleteApi eliminarVotacion(@RequestParam(value = "id", required = true) final int id) {
		final DeleteApi vapi = new DeleteApi();

		try {
			this.preguntaService.delete(this.preguntaService.findOne(id));
			vapi.setExito(true);
			vapi.setMensaje("Eliminado con éxito");
		} catch (final IllegalArgumentException e) {
			vapi.setExito(false);
			vapi.setMensaje("Error al eliminar");
		}

		return vapi;
	}
}
