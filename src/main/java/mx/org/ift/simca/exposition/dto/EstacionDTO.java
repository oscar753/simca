package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.Date;

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
	
	private String folioRPCUMCA;
	private CatalogoDTO tipoClase;
	private CatalogoDTO concesionario;
	private String frecuenciaAM;
	private String frecuenciaFM;
	private Date vigenciaIni;
	private Date vigenciaFin;
	private CatalogoDTO tipoFrecuencia;
	private CatalogoDTO tipoEstacion;
	private String nombreProgramacion;
	private String multiprograma;
	
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
	public String getFolioRPCUMCA() {
		return folioRPCUMCA;
	}
	public void setFolioRPCUMCA(String folioRPCUMCA) {
		this.folioRPCUMCA = folioRPCUMCA;
	}
	public CatalogoDTO getTipoClase() {
		return tipoClase;
	}
	public void setTipoClase(CatalogoDTO tipoClase) {
		this.tipoClase = tipoClase;
	}
	public CatalogoDTO getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(CatalogoDTO concesionario) {
		this.concesionario = concesionario;
	}
	public String getFrecuenciaAM() {
		return frecuenciaAM;
	}
	public void setFrecuenciaAM(String frecuenciaAM) {
		this.frecuenciaAM = frecuenciaAM;
	}
	public String getFrecuenciaFM() {
		return frecuenciaFM;
	}
	public void setFrecuenciaFM(String frecuenciaFM) {
		this.frecuenciaFM = frecuenciaFM;
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
	public CatalogoDTO getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(CatalogoDTO tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
	public CatalogoDTO getTipoEstacion() {
		return tipoEstacion;
	}
	public void setTipoEstacion(CatalogoDTO tipoEstacion) {
		this.tipoEstacion = tipoEstacion;
	}
	public String getNombreProgramacion() {
		return nombreProgramacion;
	}
	public void setNombreProgramacion(String nombreProgramacion) {
		this.nombreProgramacion = nombreProgramacion;
	}
	public String getMultiprograma() {
		return multiprograma;
	}
	public void setMultiprograma(String multiprograma) {
		this.multiprograma = multiprograma;
	}
	
	
}
