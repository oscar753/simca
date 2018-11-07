package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FOMRULARIO")
public class FormularioXMLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8090950755951051179L;

	private PreguntasXMLDTO preguntasXMLDTO= new PreguntasXMLDTO();

	@XmlElement(name="PREGUNTAS")
	public PreguntasXMLDTO getPreguntasXMLDTO() {
		return preguntasXMLDTO;
	}

	public void setPreguntasXMLDTO(PreguntasXMLDTO preguntasXMLDTO) {
		this.preguntasXMLDTO = preguntasXMLDTO;
	}
	
	
}
