package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PREGUNTAS")
public class PreguntasXMLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8154383032186430233L;
	
	private List<PreguntaXMLDTO> preguntaXMLDTO = new ArrayList<PreguntaXMLDTO>();

	@XmlElement(name="PREGUNTA")
	public List<PreguntaXMLDTO> getPreguntaXMLDTO() {
		return preguntaXMLDTO;
	}

	public void setPreguntaXMLDTO(List<PreguntaXMLDTO> preguntaXMLDTO) {
		this.preguntaXMLDTO = preguntaXMLDTO;
	}
	
}
