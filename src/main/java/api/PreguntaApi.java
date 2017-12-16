
package api;

public class PreguntaApi {

	private int		id;
	private String	texto_pregunta;
	private String	tipo_pregunta;
	private String	id_respuestas;
	private int		id_votacion;
	private int		id_dependencia;


	public String getId_respuestas() {
		return this.id_respuestas;
	}

	public void setId_respuestas(final String id_respuestas) {
		this.id_respuestas = id_respuestas;
	}

	public String getTexto_pregunta() {
		return this.texto_pregunta;
	}
	public void setTexto_pregunta(final String texto_pregunta) {
		this.texto_pregunta = texto_pregunta;
	}
	public String getTipo_pregunta() {
		return this.tipo_pregunta;
	}
	public void setTipo_pregunta(final String tipo_pregunta) {
		this.tipo_pregunta = tipo_pregunta;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getId_votacion() {
		return this.id_votacion;
	}

	public void setId_votacion(final int id_votacion) {
		this.id_votacion = id_votacion;
	}

	public int getId_dependencia() {
		return this.id_dependencia;
	}

	public void setId_dependencia(final int id_dependencia) {
		this.id_dependencia = id_dependencia;
	}

}
