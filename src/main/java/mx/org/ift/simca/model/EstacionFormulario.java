package mx.org.ift.simca.model;

import java.io.Serializable;
import java.util.List;

public class EstacionFormulario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386936470065139533L;
	
	private String folioElectronico;
	private Integer idPregunta;
	private String pregunta;
	private Opcion opcion;

	public String getFolioElectronico() {
		return folioElectronico;
	}

	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	
}
