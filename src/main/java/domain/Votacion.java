
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Votacion extends DomainEntity {

	private Integer	id_censo;
	private Integer	id_grupo;
	private String	titulo;
	private String	descripción;
	private Date	fecha_ini;
	private Date	fecha_fin;
	private String	clave;


	public Integer getId_censo() {
		return this.id_censo;
	}

	public void setId_censo(final Integer id_censo) {
		this.id_censo = id_censo;
	}

	public Integer getId_grupo() {
		return this.id_grupo;
	}

	public void setId_grupo(final Integer id_grupo) {
		this.id_grupo = id_grupo;
	}

	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	@NotBlank
	public String getDescripción() {
		return this.descripción;
	}

	public void setDescripción(final String descripción) {
		this.descripción = descripción;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFecha_ini() {
		return this.fecha_ini;
	}

	public void setFecha_ini(final Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getFecha_fin() {
		return this.fecha_fin;
	}

	public void setFecha_fin(final Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@NotBlank
	public String getClave() {
		return this.clave;
	}

	public void setClave(final String clave) {
		this.clave = clave;
	}


	// Relationships

	private Collection<Pregunta> preguntas;


	@OneToMany(mappedBy = "votacion")
	public Collection<Pregunta> getPreguntas() {
		return this.preguntas;
	}

	public void setPreguntas(final Collection<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

}
