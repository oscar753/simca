/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class TipoUsoEstacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4624671621152797692L;

	private Integer idTipoUsoEstacion;
	
	private String tipoUsoEstacion;

	public Integer getIdTipoUsoEstacion() {
		return idTipoUsoEstacion;
	}

	public void setIdTipoUsoEstacion(Integer idTipoUsoEstacion) {
		this.idTipoUsoEstacion = idTipoUsoEstacion;
	}

	public String getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}

	public void setTipoUsoEstacion(String tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}

	
}
