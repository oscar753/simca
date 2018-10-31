package mx.org.ift.simca.model;

import java.io.Serializable;

public class TipoFrecuencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -890589723568420383L;
	
	private Integer idTipoFrecuencia;
	private String tipoFrecuencia;
	
	public Integer getIdTipoFrecuencia() {
		return idTipoFrecuencia;
	}
	public void setIdTipoFrecuencia(Integer idTipoFrecuencia) {
		this.idTipoFrecuencia = idTipoFrecuencia;
	}
	public String getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(String tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
	
}
