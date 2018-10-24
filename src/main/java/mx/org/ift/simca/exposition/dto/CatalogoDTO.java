/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CatalogoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8072140569292128569L;

	private String identificador;
	
	private String descripcion;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
