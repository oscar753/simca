/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class Cobertura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5829058785178462345L;
	
	private Integer idCanal;
	private Integer idEstado;
	private Integer idPoblacion;
	private Integer noCanal;
	private Estado estado;
	private Poblacion poblacion;
	
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
	 * @return the noCanal
	 */
	public Integer getNoCanal() {
		return noCanal;
	}
	/**
	 * @param noCanal the noCanal to set
	 */
	public void setNoCanal(Integer noCanal) {
		this.noCanal = noCanal;
	}
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	/**
	 * @return the poblacion
	 */
	public Poblacion getPoblacion() {
		return poblacion;
	}
	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}
	
}
