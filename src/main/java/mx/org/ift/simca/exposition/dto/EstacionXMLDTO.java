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
@XmlType(propOrder = { "folioElectronico", "idSenial", "idClase", "idTipoUsoEstacion", "distintivo", "idBanda", "idTipoFrecuencia", "frecuencia", "fecIniVigencia", "fecFinVigencia" })
public class EstacionXMLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5133481733301543740L;
	
	private String folioElectronico;
	private String idSenial;
	private String idClase;
	private String idTipoUsoEstacion;
	private String distintivo;
	private String idBanda;
	private String idTipoFrecuencia;
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
	
	@XmlElement(name="ID_CLASE")
	public String getIdClase() {
		return idClase;
	}
	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}
	
	@XmlElement(name="ID_TIPO_USO_ESTACION")
	public String getIdTipoUsoEstacion() {
		return idTipoUsoEstacion;
	}
	public void setIdTipoUsoEstacion(String idTipoUsoEstacion) {
		this.idTipoUsoEstacion = idTipoUsoEstacion;
	}
	
	@XmlElement(name="ID_TIPO_FRECUENCIA")
	public String getIdTipoFrecuencia() {
		return idTipoFrecuencia;
	}
	public void setIdTipoFrecuencia(String idTipoFrecuencia) {
		this.idTipoFrecuencia = idTipoFrecuencia;
	}
	
	@XmlElement(name="DISTINTIVO")
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	
	@XmlElement(name="ID_BANDA")
	public String getIdBanda() {
		return idBanda;
	}
	public void setIdBanda(String idBanda) {
		this.idBanda = idBanda;
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
