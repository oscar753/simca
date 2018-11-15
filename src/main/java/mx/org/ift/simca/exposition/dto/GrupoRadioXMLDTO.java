package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="GRUPO_RADIO")
@XmlType(propOrder = { "folioElectronico", "idConcesionario" })
public class GrupoRadioXMLDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 86356278405807545L;

	private String folioElectronico;
	private String idConcesionario;
	
	@XmlElement(name="FOLIO_ELECTRONICO")
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	
	@XmlElement(name="ID_CONCESIONARIO")
	public String getIdConcesionario() {
		return idConcesionario;
	}
	public void setIdConcesionario(String idConcesionario) {
		this.idConcesionario = idConcesionario;
	}
	
}
