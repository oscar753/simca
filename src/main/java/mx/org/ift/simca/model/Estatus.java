/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Estatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059186572616997653L;
	
	private Integer idEstatus;
	private String estatus;
	
	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
