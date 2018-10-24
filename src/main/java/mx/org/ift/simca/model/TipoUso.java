/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class TipoUso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2446738561446050549L;

	private Integer idTipoUso;
	
	private String tipoUso;

	/**
	 * @return the idTipoUso
	 */
	public Integer getIdTipoUso() {
		return idTipoUso;
	}

	/**
	 * @param idTipoUso the idTipoUso to set
	 */
	public void setIdTipoUso(Integer idTipoUso) {
		this.idTipoUso = idTipoUso;
	}

	/**
	 * @return the tipoUso
	 */
	public String getTipoUso() {
		return tipoUso;
	}

	/**
	 * @param tipoUso the tipoUso to set
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}
	
}
