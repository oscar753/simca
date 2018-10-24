package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

public class EstacionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6483812316190512455L;

	private String numero;
	private String tipoUso;
	private String concesionario;
	private String distintivo;
	private String banda;
	private String frecuenciaFM;
	private String frecuenciaAM;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipoUso() {
		return tipoUso;
	}
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}
	public String getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	public String getBanda() {
		return banda;
	}
	public void setBanda(String banda) {
		this.banda = banda;
	}
	public String getFrecuenciaFM() {
		return frecuenciaFM;
	}
	public void setFrecuenciaFM(String frecuenciaFM) {
		this.frecuenciaFM = frecuenciaFM;
	}
	public String getFrecuenciaAM() {
		return frecuenciaAM;
	}
	public void setFrecuenciaAM(String frecuenciaAM) {
		this.frecuenciaAM = frecuenciaAM;
	}

	
}
