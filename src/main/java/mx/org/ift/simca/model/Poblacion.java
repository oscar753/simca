/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Poblacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5499730323705789409L;
	
	private Integer idEstado;
	
	private Integer idPoblacion;
	
	private String poblacion;

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
	 * @return the idPoblacion
	 */
	public Integer getIdPoblacion() {
		return idPoblacion;
	}

	/**
	 * @param idPoblacion the idPoblacion to set
	 */
	public void setIdPoblacion(Integer idPoblacion) {
		this.idPoblacion = idPoblacion;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

}
