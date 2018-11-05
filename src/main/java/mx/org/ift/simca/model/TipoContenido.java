package mx.org.ift.simca.model;

import java.io.Serializable;

public class TipoContenido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717793770293467580L;

	private Integer idTipoContenido;
	
	private String tipoContenido;

	/**
	 * @return the idTipoContenido
	 */
	public Integer getIdTipoContenido() {
		return idTipoContenido;
	}

	/**
	 * @param idTipoContenido the idTipoContenido to set
	 */
	public void setIdTipoContenido(Integer idTipoContenido) {
		this.idTipoContenido = idTipoContenido;
	}

	/**
	 * @return the tipoContenido
	 */
	public String getTipoContenido() {
		return tipoContenido;
	}

	/**
	 * @param tipoContenido the tipoContenido to set
	 */
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	
	
}
