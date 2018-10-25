/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Concesionario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1420892777695508534L;

	private Integer idConcesionario;
	
	private String nomConcesionario;

	/**
	 * @return the idConcesionario
	 */
	public Integer getIdConcesionario() {
		return idConcesionario;
	}

	/**
	 * @param idConcesionario the idConcesionario to set
	 */
	public void setIdConcesionario(Integer idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	/**
	 * @return the nomConcesionario
	 */
	public String getNomConcesionario() {
		return nomConcesionario;
	}

	/**
	 * @param nomConcesionario the nomConcesionario to set
	 */
	public void setNomConcesionario(String nomConcesionario) {
		this.nomConcesionario = nomConcesionario;
	}

}
