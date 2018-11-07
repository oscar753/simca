package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="PREGUNTA")
@XmlType(propOrder = { "idPregunta", "descPregunta", "respuesta" })
public class PreguntaXMLDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7274449719397011796L;
	
	private String idPregunta;
	private String descPregunta;
	private String respuesta;
	
	@XmlElement(name="ID_PREGUNTA")
	public String getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	@XmlElement(name="DESC_PREGUNTA")
	public String getDescPregunta() {
		return descPregunta;
	}
	public void setDescPregunta(String descPregunta) {
		this.descPregunta = descPregunta;
	}
	
	@XmlElement(name="RESPUESTA")
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
