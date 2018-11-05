package mx.org.ift.simca.model;

import java.io.Serializable;
import java.util.Date;

public class Estacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8580230360450842942L;

	private String folioElectronico;
	private Integer idSenial;
	private String distintivo;
	private String frecuencia;
	private Date vigenciaIni;
	private Date vigenciaFin;
	private Poblacion poblacion;
	private Estado estado;
	private Clase clase;
	private TipoUsoEstacion tipoUsoEstacion;
	private Banda banda;
	private TipoFrecuencia tipoFrecuencia;
	private Concesionario concesionario;
	private GrupoRadio grupoRadio;
	
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	public Integer getIdSenial() {
		return idSenial;
	}
	public void setIdSenial(Integer idSenial) {
		this.idSenial = idSenial;
	}
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public Date getVigenciaIni() {
		return vigenciaIni;
	}
	public void setVigenciaIni(Date vigenciaIni) {
		this.vigenciaIni = vigenciaIni;
	}
	public Date getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
	public Poblacion getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public TipoUsoEstacion getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}
	public void setTipoUsoEstacion(TipoUsoEstacion tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}
	public Banda getBanda() {
		return banda;
	}
	public void setBanda(Banda banda) {
		this.banda = banda;
	}
	public TipoFrecuencia getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(TipoFrecuencia tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
	public Concesionario getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}
	public GrupoRadio getGrupoRadio() {
		return grupoRadio;
	}
	public void setGrupoRadio(GrupoRadio grupoRadio) {
		this.grupoRadio = grupoRadio;
	}
	
}
