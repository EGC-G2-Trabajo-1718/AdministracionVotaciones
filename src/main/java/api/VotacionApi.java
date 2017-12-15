
package api;

public class VotacionApi {

	private int		id;
	private int		id_censo;
	private int		id_grupo;
	private String	titulo;
	private String	descripcion;
	private String	fecha_ini;
	private String	fecha_fin;
	private String	clave;
	private String	id_preguntas;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getId_censo() {
		return this.id_censo;
	}

	public void setId_censo(final int id_censo) {
		this.id_censo = id_censo;
	}

	public int getId_grupo() {
		return this.id_grupo;
	}

	public void setId_grupo(final int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha_ini() {
		return this.fecha_ini;
	}

	public void setFecha_ini(final String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public String getFecha_fin() {
		return this.fecha_fin;
	}

	public void setFecha_fin(final String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(final String clave) {
		this.clave = clave;
	}

	public String getId_preguntas() {
		return this.id_preguntas;
	}

	public void setId_preguntas(final String id_preguntas) {
		this.id_preguntas = id_preguntas;
	}

}
