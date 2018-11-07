/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Grupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5019174327701220606L;
	
	private Integer idGrupo;
	
	private String grupo;

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
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
}
