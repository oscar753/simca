/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CoberturaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269755983930323204L;

	private PoblacionDTO poblacion;

	/**
	 * @return the poblacion
	 */
	public PoblacionDTO getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(PoblacionDTO poblacion) {
		this.poblacion = poblacion;
	}
	
}
