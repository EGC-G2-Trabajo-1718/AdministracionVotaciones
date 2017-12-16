
package api;

public class RespuestaApi {

	private String	texto_respuesta;
	private int		pregunta;


	public String getTexto_respuesta() {
		return this.texto_respuesta;
	}

	public void setTexto_respuesta(final String texto_respuesta) {
		this.texto_respuesta = texto_respuesta;
	}

	public int getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(final int pregunta) {
		this.pregunta = pregunta;
	}

}
