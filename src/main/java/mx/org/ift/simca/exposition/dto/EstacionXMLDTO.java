package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.org.ift.simca.model.CoberturaRadio;
import mx.org.ift.simca.model.Estacion;
import mx.org.ift.simca.model.GrupoRadio;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ESTACION")
@XmlType(propOrder = { "folioElectronico", "idSenial", "clase", "tipoUsoEstacion", "distintivo", "banda", "tipoFrecuencia", "frecuencia", "fecIniVigencia", "fecFinVigencia" })
public class EstacionXMLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5133481733301543740L;
	
	private String folioElectronico;
	private String idSenial;
	private String clase;
	private String tipoUsoEstacion;
	private String distintivo;
	private String banda;
	private String tipoFrecuencia;
	private String frecuencia;
	private String fecIniVigencia;
	private String fecFinVigencia;
	
	@XmlElement(name="FOLIO_ELECTRONICO")
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	
	@XmlElement(name="ID_SENIAL")
	public String getIdSenial() {
		return idSenial;
	}
	public void setIdSenial(String idSenial) {
		this.idSenial = idSenial;
	}
	
	@XmlElement(name="CLASE")
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	
	@XmlElement(name="TIPO_USO_ESTACION")
	public String getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}
	public void setTipoUsoEstacion(String tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}
	
	@XmlElement(name="DISTINTIVO")
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	
	@XmlElement(name="BANDA")
	public String getBanda() {
		return banda;
	}
	public void setBanda(String banda) {
		this.banda = banda;
	}
	
	@XmlElement(name="TIPO_FRECUENCIA")
	public String getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(String tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
	
	@XmlElement(name="FRECUENCIA")
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	@XmlElement(name="FEC_INI_VIGENCIA")
	public String getFecIniVigencia() {
		return fecIniVigencia;
	}
	public void setFecIniVigencia(String fecIniVigencia) {
		this.fecIniVigencia = fecIniVigencia;
	}
	
	@XmlElement(name="FEC_FIN_VIGENCIA")
	public String getFecFinVigencia() {
		return fecFinVigencia;
	}
	public void setFecFinVigencia(String fecFinVigencia) {
		this.fecFinVigencia = fecFinVigencia;
	}
	
}
