package mx.org.ift.simca.model;

import java.io.Serializable;

public class TipoPregunta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028325849202258190L;

	private Integer idTipoFormulario;
	private Integer idPregunta;
	private Integer idTipoPregunta;
	private String pregunta;
	
	public Integer getIdTipoFormulario() {
		return idTipoFormulario;
	}
	public void setIdTipoFormulario(Integer idTipoFormulario) {
		this.idTipoFormulario = idTipoFormulario;
	}
	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public Integer getIdTipoPregunta() {
		return idTipoPregunta;
	}
	public void setIdTipoPregunta(Integer idTipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
}
