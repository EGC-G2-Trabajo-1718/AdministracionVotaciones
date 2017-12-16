
package controllers;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import api.RespuestaApi;
import domain.Pregunta;
import domain.Respuesta;
import services.PreguntaService;
import services.RespuestaService;
import services.VotacionService;

@Controller
@RequestMapping("/api")
public class RespuestaController {

	@Autowired
	private PreguntaService		preguntaService;

	@Autowired
	private RespuestaService	respuestaService;

	@Autowired
	private VotacionService		votacionService;


	public RespuestaController() {
		super();
	}

	@RequestMapping(value = "/get/respuesta", method = RequestMethod.GET)
	public @ResponseBody RespuestaApi ObtenerPregunta(@RequestParam(value = "id", required = true) final int id) {
		final Respuesta r = this.respuestaService.findOne(id);
		final RespuestaApi vapi = new RespuestaApi();

		vapi.setPregunta(r.getPregunta().getId());
		vapi.setTexto_respuesta(r.getTexto_respuesta());
		return vapi;
	}

	@RequestMapping(value = "/get/respuestas", method = RequestMethod.GET)
	public @ResponseBody ArrayList<RespuestaApi> ObtenerPreguntas(@RequestParam(value = "id", required = true) final int id) {
		final Pregunta p = this.preguntaService.findOne(id);

		final ArrayList<RespuestaApi> lista = new ArrayList<RespuestaApi>();
		for (final Respuesta r : p.getRespuestas()) {
			final RespuestaApi aux = new RespuestaApi();
			aux.setPregunta(r.getPregunta().getId());
			aux.setTexto_respuesta(r.getTexto_respuesta());
		}

		return lista;
	}

	@RequestMapping(value = "/post/respuestaCreate", method = RequestMethod.POST)
	public @ResponseBody RespuestaApi crearPregunta(@RequestParam(value = "texto_respuesta", required = true) final String texto_respuesta, @RequestParam(value = "pregunta", required = true) final int pregunta) throws ParseException {
		final RespuestaApi vapi = new RespuestaApi();
		final Respuesta respuesta = new Respuesta();
		respuesta.setPregunta(this.preguntaService.findOne(pregunta));
		vapi.setPregunta(pregunta);

		respuesta.setTexto_respuesta(texto_respuesta);
		vapi.setTexto_respuesta(texto_respuesta);

		return vapi;
	}

	@RequestMapping(value = "/post/respuestaEdit", method = RequestMethod.POST)
	public @ResponseBody RespuestaApi editarRespuesta(@RequestParam(value = "id", required = true) final int id, @RequestParam(value = "texto_respuesta", required = false) final String texto_respuesta,
		@RequestParam(value = "pregunta", required = false) final Integer pregunta) throws ParseException {
		final RespuestaApi vapi = new RespuestaApi();
		final Respuesta respuesta = this.respuestaService.findOne(id);

		if (texto_respuesta != null) {
			vapi.setTexto_respuesta(texto_respuesta);
			respuesta.setTexto_respuesta(texto_respuesta);
		}
		if (pregunta != null) {
			vapi.setPregunta(pregunta);
			respuesta.setPregunta(this.preguntaService.findOne(id));
		}

		return vapi;
	}

}
