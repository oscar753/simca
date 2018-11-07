/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class PreguntaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7154225820554752633L;
	
	private String id_pregunta;
	private String desc_pregunta;
	private String respuesta;
	
	/**
	 * @return the id_pregunta
	 */
	public String getId_pregunta() {
		return id_pregunta;
	}
	/**
	 * @param id_pregunta the id_pregunta to set
	 */
	public void setId_pregunta(String id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
	/**
	 * @return the desc_pregunta
	 */
	public String getDesc_pregunta() {
		return desc_pregunta;
	}
	/**
	 * @param desc_pregunta the desc_pregunta to set
	 */
	public void setDesc_pregunta(String desc_pregunta) {
		this.desc_pregunta = desc_pregunta;
	}
	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
