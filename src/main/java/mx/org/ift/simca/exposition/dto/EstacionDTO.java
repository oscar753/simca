package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

public class EstacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6483812316190512455L;

	private String numero;
	private CatalogoDTO tipoUsoEstacion;
	private String distintivo;
	private CatalogoDTO banda;
	private String frecuencia;

	private CatalogoDTO poblacion;
	private CatalogoDTO estado;
	private GrupoRadioDTO grupoRadio;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public CatalogoDTO getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}
	public void setTipoUsoEstacion(CatalogoDTO tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	public CatalogoDTO getBanda() {
		return banda;
	}
	public void setBanda(CatalogoDTO banda) {
		this.banda = banda;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public CatalogoDTO getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(CatalogoDTO poblacion) {
		this.poblacion = poblacion;
	}
	public CatalogoDTO getEstado() {
		return estado;
	}
	public void setEstado(CatalogoDTO estado) {
		this.estado = estado;
	}
	public GrupoRadioDTO getGrupoRadio() {
		return grupoRadio;
	}
	public void setGrupoRadio(GrupoRadioDTO grupoRadio) {
		this.grupoRadio = grupoRadio;
	}
	
	
}
