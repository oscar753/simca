/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class GrupoCanal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7602889018933260096L;
	
	private Integer idGrupo;
	
	private Integer idCanal;

	/**
	 * @return the idGrupo
	 */
	public Integer getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return the idCanal
	 */
	public Integer getIdCanal() {
		return idCanal;
	}

	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(Integer idCanal) {
		this.idCanal = idCanal;
	}

}
