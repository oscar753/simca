package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mx.org.ift.simca.model.Poblacion;

@XmlRootElement(name="COBERTURA")
public class CoberturaXMLDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2069597107619772703L;
	
	private List<PoblacionXMLDTO> poblaciones = new ArrayList<PoblacionXMLDTO>();

	@XmlElement(name="POBLACION")
	public List<PoblacionXMLDTO> getPoblaciones() {
		return poblaciones;
	}

	public void setPoblaciones(List<PoblacionXMLDTO> poblaciones) {
		this.poblaciones = poblaciones;
	}
	
}
