/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Tercero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 191652435558184545L;
	
	private Integer idTercero;	
	private String tercero;
	
	/**
	 * @return the idTercero
	 */
	public Integer getIdTercero() {
		return idTercero;
	}
	/**
	 * @param idTercero the idTercero to set
	 */
	public void setIdTercero(Integer idTercero) {
		this.idTercero = idTercero;
	}
	/**
	 * @return the tercero
	 */
	public String getTercero() {
		return tercero;
	}
	/**
	 * @param tercero the tercero to set
	 */
	public void setTercero(String tercero) {
		this.tercero = tercero;
	}

}
