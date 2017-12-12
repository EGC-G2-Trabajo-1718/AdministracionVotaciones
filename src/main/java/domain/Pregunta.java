
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Pregunta extends DomainEntity {

	private String	texto_pregunta;
	private String	tipo_pregunta;


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


	// Relationships
	private Collection<Respuesta>	respuestas;
	private Votacion				votacion;
	private Pregunta				dependencia;


	@Valid
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public Pregunta getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(final Pregunta dependencia) {
		this.dependencia = dependencia;
	}

	@Valid
	@OneToMany(mappedBy = "pregunta")
	public Collection<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(final Collection<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Valid
	@ManyToOne
	public Votacion getVotacion() {
		return this.votacion;
	}

	public void setVotacion(final Votacion votacion) {
		this.votacion = votacion;
	}
}