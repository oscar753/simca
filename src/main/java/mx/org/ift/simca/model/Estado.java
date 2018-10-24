/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -493277931313298278L;

	private Integer idEstado;
	
	private String estado;

	/**
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
