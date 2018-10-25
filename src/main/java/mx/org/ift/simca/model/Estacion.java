package mx.org.ift.simca.model;

import java.io.Serializable;
import java.util.Date;

public class Estacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8580230360450842942L;

	private String distintivo;
	private String poblacion;
	private String estado;
	private String tipoUsoEstacion;
	private String concesionario;
	private String banda;
	private Long frecuencia;
	
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}
	public void setTipoUsoEstacion(String tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}
	public String getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
	public String getBanda() {
		return banda;
	}
	public void setBanda(String banda) {
		this.banda = banda;
	}
	public Long getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(Long frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	
}
