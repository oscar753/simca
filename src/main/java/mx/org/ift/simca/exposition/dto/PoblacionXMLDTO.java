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
@XmlType(propOrder = { "idEstado", "idPoblacion" })
public class PoblacionXMLDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 731500510177345404L;
	
	private String idEstado;
	private String idPoblacion;

	@XmlElement(name="ID_ESTADO")
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	
	@XmlElement(name="ID_POBLACION")
	public String getIdPoblacion() {
		return idPoblacion;
	}
	public void setIdPoblacion(String idPoblacion) {
		this.idPoblacion = idPoblacion;
	}
	
}
