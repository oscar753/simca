package mx.org.ift.simca.model;

import java.io.Serializable;
import java.util.Date;

public class Estacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8580230360450842942L;

	private String folioElectronico;
	
	private Long idSenial;

	private Long idEstado;
	
	private Long idPoblacion;
	
	private Long idClase;
	
	private Long idTipoUsoEstacion;
	
	private String distintivo;
	
	private Long idBanda;
	
	private Long idTipoFrecuencia;
	
	private Long frecuencia;

	private Date fecIniVigencia;
	
	private Date fecFinVigencia;

	
	public String getFolioElectronico() {
		return folioElectronico;
	}

	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}

	public Long getIdSenial() {
		return idSenial;
	}

	public void setIdSenial(Long idSenial) {
		this.idSenial = idSenial;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdPoblacion() {
		return idPoblacion;
	}

	public void setIdPoblacion(Long idPoblacion) {
		this.idPoblacion = idPoblacion;
	}

	public Long getIdClase() {
		return idClase;
	}

	public void setIdClase(Long idClase) {
		this.idClase = idClase;
	}

	public Long getIdTipoUsoEstacion() {
		return idTipoUsoEstacion;
	}

	public void setIdTipoUsoEstacion(Long idTipoUsoEstacion) {
		this.idTipoUsoEstacion = idTipoUsoEstacion;
	}

	public String getDistintivo() {
		return distintivo;
	}

	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}

	public Long getIdBanda() {
		return idBanda;
	}

	public void setIdBanda(Long idBanda) {
		this.idBanda = idBanda;
	}

	public Long getIdTipoFrecuencia() {
		return idTipoFrecuencia;
	}

	public void setIdTipoFrecuencia(Long idTipoFrecuencia) {
		this.idTipoFrecuencia = idTipoFrecuencia;
	}

	public Long getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Long frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Date getFecIniVigencia() {
		return fecIniVigencia;
	}

	public void setFecIniVigencia(Date fecIniVigencia) {
		this.fecIniVigencia = fecIniVigencia;
	}

	public Date getFecFinVigencia() {
		return fecFinVigencia;
	}

	public void setFecFinVigencia(Date fecFinVigencia) {
		this.fecFinVigencia = fecFinVigencia;
	}
	
	
	
}
