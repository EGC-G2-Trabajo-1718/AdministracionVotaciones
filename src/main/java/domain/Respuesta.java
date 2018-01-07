
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Respuesta extends DomainEntity {

	private String	texto_respuesta;
	private Boolean	seleccionada;


	@NotBlank
	public String getTexto_respuesta() {
		return this.texto_respuesta;
	}

	public void setTexto_respuesta(final String texto_respuesta) {
		this.texto_respuesta = texto_respuesta;
	}

	public Boolean getSeleccionada() {
		return this.seleccionada;
	}

	public void setSeleccionada(final Boolean seleccionada) {
		this.seleccionada = seleccionada;
	}


	// Relationships
	private Pregunta pregunta;


	@ManyToOne
	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(final Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
