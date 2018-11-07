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

@XmlRootElement(name="POBLACION")
@XmlType(propOrder = { "estado", "municipio" })
public class PoblacionXMLDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 731500510177345404L;
	
	private String estado;
	private String municipio;
	
	@XmlElement(name="ESTADO")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@XmlElement(name="MUNICIPIO")
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
}
