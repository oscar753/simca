package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "XML")
@XmlType(propOrder = { "grupoRadioXMLDTO", "estacionXMLDTO", "coberturaXMLDTO", "formularioXMLDTO" })
public class RadioXMLDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488071062820786567L;

	GrupoRadioXMLDTO grupoRadioXMLDTO = new GrupoRadioXMLDTO();
	EstacionXMLDTO estacionXMLDTO = new EstacionXMLDTO();
	CoberturaXMLDTO coberturaXMLDTO = new CoberturaXMLDTO();
	FormularioXMLDTO formularioXMLDTO = new FormularioXMLDTO();

	@XmlElement(name = "GRUPO_RADIO")
	public GrupoRadioXMLDTO getGrupoRadioXMLDTO() {
		return grupoRadioXMLDTO;
	}

	public void setGrupoRadioXMLDTO(GrupoRadioXMLDTO grupoRadioXMLDTO) {
		this.grupoRadioXMLDTO = grupoRadioXMLDTO;
	}

	@XmlElement(name = "ESTACION")
	public EstacionXMLDTO getEstacionXMLDTO() {
		return estacionXMLDTO;
	}

	public void setEstacionXMLDTO(EstacionXMLDTO estacionXMLDTO) {
		this.estacionXMLDTO = estacionXMLDTO;
	}

	@XmlElement(name = "COBERTURA")
	public CoberturaXMLDTO getCoberturaXMLDTO() {
		return coberturaXMLDTO;
	}

	public void setCoberturaXMLDTO(CoberturaXMLDTO coberturaXMLDTO) {
		this.coberturaXMLDTO = coberturaXMLDTO;
	}

	@XmlElement(name = "FORMULARIO")
	public FormularioXMLDTO getFormularioXMLDTO() {
		return formularioXMLDTO;
	}

	public void setFormularioXMLDTO(FormularioXMLDTO formularioXMLDTO) {
		this.formularioXMLDTO = formularioXMLDTO;
	}

}
